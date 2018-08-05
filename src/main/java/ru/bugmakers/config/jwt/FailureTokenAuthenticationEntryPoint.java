package ru.bugmakers.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import ru.bugmakers.dto.response.MbResponse;
import ru.bugmakers.exceptions.MbError;
import ru.bugmakers.exceptions.MbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ivan
 */
public class FailureTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MbException exception = MbException.create(MbError.ACE01);
        MbResponse mbResponse = MbResponse.unauth(exception);
        String rs = mapper.writeValueAsString(mbResponse);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(rs);
        response.flushBuffer();
    }

}
