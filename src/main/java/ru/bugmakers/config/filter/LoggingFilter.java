package ru.bugmakers.config.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.bugmakers.config.jwt.filter.CapturingRequestWrapper;
import ru.bugmakers.config.jwt.filter.CapturingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Ivan
 */
public class LoggingFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPTY_STRING = "";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (LOGGER.isDebugEnabled()) {
            CapturingResponseWrapper capturingResponseWrapper = new CapturingResponseWrapper(response);

            if (request.getContentType() != null && request.getContentType().startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                logRequest(request);
                filterChain.doFilter(request, capturingResponseWrapper);
            } else {
                CapturingRequestWrapper capturingRequestWrapper = new CapturingRequestWrapper(request);
                logRequest(capturingRequestWrapper);
                filterChain.doFilter(capturingRequestWrapper, capturingResponseWrapper);
            }

            logResponse(request, capturingResponseWrapper);
            response.getWriter().write(capturingResponseWrapper.getCaptureAsString());
        } else {
            filterChain.doFilter(request, response);
        }

    }

    private void logRequest(HttpServletRequest request) {
        LOGGER.debug(new StringBuilder()
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR)
                .append("+++++++++++++++++++++REQUEST_BEGIN++++++++++++++++++++").append(LINE_SEPARATOR)
                .append(String.format("URL:      %s", request.getRequestURL())).append(LINE_SEPARATOR)
                .append(String.format("Method:   %s", request.getMethod())).append(LINE_SEPARATOR)
                .append("Params:").append(LINE_SEPARATOR)
                .append(logParams(request.getParameterMap()))
                .append("Headers:").append(LINE_SEPARATOR)
                .append(logRequestHeaders(request))
                .append(logPostParams(request)).append(LINE_SEPARATOR)
                .append("++++++++++++++++++++++REQUEST_END+++++++++++++++++++++").append(LINE_SEPARATOR)
                .toString()
        );
    }

    private void logResponse(HttpServletRequest request, CapturingResponseWrapper response) {
        LOGGER.debug(new StringBuilder()
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR)
                .append("+++++++++++++++++++++RESPONSE_BEGIN++++++++++++++++++++").append(LINE_SEPARATOR)
                .append(String.format("URL:      %s", request.getRequestURL())).append(LINE_SEPARATOR)
                .append(String.format("Method:   %s", request.getMethod())).append(LINE_SEPARATOR)
                .append("Params:").append(LINE_SEPARATOR)
                .append(logParams(request.getParameterMap()))
                .append("Headers:").append(LINE_SEPARATOR)
                .append(logResponseHeaders(response))
                .append(String.format("ContentType:  %s", response.getContentType())).append(LINE_SEPARATOR)
                .append(logResponseContent(response)).append(LINE_SEPARATOR)
                .append("++++++++++++++++++++++RESPONSE_END+++++++++++++++++++++").append(LINE_SEPARATOR)
                .toString()
        );
    }

    private String logResponseContent(CapturingResponseWrapper response) {
        StringBuilder sb = new StringBuilder("Response:");
        try {
            sb.append(LINE_SEPARATOR).append(prettyPrintResponse(response));
        } catch (Exception e) {
            sb.append("Response content not available");
        }
        return sb.toString();
    }

    private String logPostParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        if (request.getMethod().equalsIgnoreCase("POST")) {
            sb.append(String.format("ContentType:  %s", request.getContentType()));
            String json = null;
            if (request.getContentType() == null || !request.getContentType().startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                json = getJson(request);
            }
            if (!Strings.isNullOrEmpty(json)) {
                sb
                        .append("Json")
                        .append(LINE_SEPARATOR)
                        .append(json);
            }
        }
        return sb.toString();
    }

    private String prettyPrintResponse(CapturingResponseWrapper response) throws IOException, TransformerConfigurationException {
        String rs = EMPTY_STRING;
        String contentType = response.getContentType();
        if (contentType != null) {
            if (contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
                rs = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(OBJECT_MAPPER.readTree(response.getCaptureAsString()));
            } else {
                rs = response.getCaptureAsString();
            }
        }
        return rs;
    }

    private String logParams(Map<String, String[]> parameterMap) {
        StringBuilder sb = new StringBuilder();
        if (!Collections.isEmpty(parameterMap)) {
            parameterMap.forEach(((k, v) -> sb.append(String.format("          %s=%s", k, stringArrayToLine(v))).append(LINE_SEPARATOR)));
        }
        return sb.toString();
    }

    private String stringArrayToLine(String[] v) {
        if (v == null || v.length == 0) return EMPTY_STRING;
        StringBuilder sb = new StringBuilder();
        Arrays.stream(v).forEach(s -> sb.append(s).append(","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String logRequestHeaders(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        if (request != null && request.getHeaderNames() != null) {
            Enumeration<String> names = request.getHeaderNames();
            while (names.hasMoreElements()) {
                String header = names.nextElement();
                sb.append(String.format("          %s: %s", header, request.getHeader(header))).append(LINE_SEPARATOR);
            }
        }
        return sb.toString();
    }

    private String logResponseHeaders(CapturingResponseWrapper response) {
        StringBuilder sb = new StringBuilder();
        if (response != null && CollectionUtils.isNotEmpty(response.getHeaderNames())) {
            Collection<String> names = response.getHeaderNames();
            names.forEach(header ->
                    sb
                            .append(String.format("          %s: %s", header, response.getHeader(header)))
                            .append(LINE_SEPARATOR));

        }
        return sb.toString();
    }

    private String getJson(HttpServletRequest request) {
        if (request == null) return EMPTY_STRING;
        String json;
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(request.getReader());
            json = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode != null ? jsonNode : EMPTY_STRING);
        } catch (Exception e) {
            json = EMPTY_STRING;
        }
        return json;
    }


}
