package ru.bugmakers.config.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.jsonwebtoken.lang.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import static ru.bugmakers.utils.MultipartUtils.findJsonPart;

/**
 * Created by Ivan
 */
public class LoggingHttpRequestInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHttpRequestInterceptor.class);
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPTY_STRING = "";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logRequest(request);
        return true;
    }

    private void logRequest(HttpServletRequest request) {
        LOGGER.debug(EMPTY_STRING);
        LOGGER.debug("+++++++++++++++++++++REQUEST_BEGIN++++++++++++++++++++");
        LOGGER.debug("URL:      {}", request.getRequestURL());
        LOGGER.debug("Method:   {}", request.getMethod());
        LOGGER.debug("Params:");
        logParams(request.getParameterMap());
        LOGGER.debug("Headers:");
        logHeaders(request);
        if (request.getMethod().equalsIgnoreCase("POST")) {
            LOGGER.debug("ContentType:  {}", request.getContentType());
            String json = getJson(request);
            if (!Strings.isNullOrEmpty(json)) {
                LOGGER.debug("Json:" + LINE_SEPARATOR + json);
            }
        }
        LOGGER.debug("++++++++++++++++++++++REQUEST_END+++++++++++++++++++++");
        LOGGER.debug(EMPTY_STRING);
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

    private void logHeaders(HttpServletRequest request) {
        if (request == null || request.getHeaderNames() == null) return;
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String header = names.nextElement();
            LOGGER.debug(String.format("          %s: %s", header, request.getHeader(header)));
        }
    }

    private String getJson(HttpServletRequest request) {
        if (request == null) return EMPTY_STRING;
        String json;
        JsonNode jsonNode = null;

        try {
            if (request instanceof MultipartRequest) {
                MultipartFile jsonPart = findJsonPart((MultipartRequest) request);
                if (jsonPart != null) {
                    jsonNode = OBJECT_MAPPER.readTree(new BufferedReader(new InputStreamReader(jsonPart.getInputStream(), StandardCharsets.UTF_8)));
                }
            } else {
                jsonNode = OBJECT_MAPPER.readTree(request.getReader());
            }
            json = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (Exception e) {
            json = EMPTY_STRING;
        }

        return json;
    }
}
