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
        LOGGER.debug(EMPTY_STRING);
        LOGGER.debug("+++++++++++++++++++++REQUEST_BEGIN++++++++++++++++++++");
        LOGGER.debug("URL:      {}", request.getRequestURL());
        LOGGER.debug("Method:   {}", request.getMethod());
        LOGGER.debug("Params:");
        logParams(request.getParameterMap());
        LOGGER.debug("Headers:");
        logRequestHeaders(request);
        if (request.getMethod().equalsIgnoreCase("POST")) {
            LOGGER.debug("ContentType:  {}", request.getContentType());
            String json = null;
            if (request.getContentType() == null || !request.getContentType().startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                json = getJson(request);
            }
            if (!Strings.isNullOrEmpty(json)) {
                LOGGER.debug("Json:" + LINE_SEPARATOR + json);
            }
        }
        LOGGER.debug("++++++++++++++++++++++REQUEST_END+++++++++++++++++++++");
        LOGGER.debug(EMPTY_STRING);
    }

    private void logResponse(HttpServletRequest request, CapturingResponseWrapper response) {

        LOGGER.debug(EMPTY_STRING);
        LOGGER.debug("+++++++++++++++++++++RESPONSE_BEGIN++++++++++++++++++++");
        LOGGER.debug("URL:      {}", request.getRequestURL());
        LOGGER.debug("Method:   {}", request.getMethod());
        LOGGER.debug("Params:");
        logParams(request.getParameterMap());
        LOGGER.debug("Headers:");
        logResponseHeaders(response);
        LOGGER.debug("ContentType:  {}", response.getContentType());
        try {
            LOGGER.debug("Response:" + System.lineSeparator() + "{}", prettyPrintResponse(response));
        } catch (Exception e) {
            LOGGER.debug("Response content not available");
        }
        LOGGER.debug("++++++++++++++++++++++RESPONSE_END+++++++++++++++++++++");
        LOGGER.debug(EMPTY_STRING);

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

    private void logParams(Map<String, String[]> parameterMap) {
        if (Collections.isEmpty(parameterMap)) return;
        parameterMap.forEach(((k, v) -> LOGGER.debug(String.format("          %s=%s", k, stringArrayToLine(v)))));
    }

    private String stringArrayToLine(String[] v) {
        if (v == null || v.length == 0) return EMPTY_STRING;
        StringBuilder sb = new StringBuilder();
        Arrays.stream(v).forEach(s -> sb.append(s).append(","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void logRequestHeaders(HttpServletRequest request) {
        if (request == null || request.getHeaderNames() == null) return;
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String header = names.nextElement();
            LOGGER.debug(String.format("          %s: %s", header, request.getHeader(header)));
        }
    }

    private void logResponseHeaders(CapturingResponseWrapper response) {
        if (response == null || CollectionUtils.isEmpty(response.getHeaderNames())) return;
        Collection<String> names = response.getHeaderNames();
        names.forEach(header -> LOGGER.debug(String.format("          %s: %s", header, response.getHeader(header))));
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
