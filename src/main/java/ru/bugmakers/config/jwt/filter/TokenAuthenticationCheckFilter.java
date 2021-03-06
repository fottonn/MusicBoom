package ru.bugmakers.config.jwt.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;
import ru.bugmakers.config.jwt.TokenAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static ru.bugmakers.config.jwt.TokenData.TOKEN_NAME;

/**
 * Фильтр, валидирующий jwt токен
 * <p>
 * Created by Ivan
 */
public class TokenAuthenticationCheckFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationCheckFilter.class);

    private AuthenticationManager authenticationManager;

    public TokenAuthenticationCheckFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {

        Authentication authentication = null;

        try {
            BufferedReader reader = null;
            if (request.getContentType().startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                Part jsonPart = httpServletRequest.getParts().stream()
                        .filter(part -> part.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE))
                        .findFirst()
                        .orElse(null);
                if (jsonPart != null) {
                    reader = new BufferedReader(new InputStreamReader(jsonPart.getInputStream(), StandardCharsets.UTF_8));
                }
            } else {
                reader = request.getReader();
            }

            Assert.notNull(reader, "Reader is null");

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(reader);
            reader.close();

            JsonNode token = null;
            if (jsonNode != null) token = jsonNode.get(TOKEN_NAME);
            if (token != null) {
                authentication = authenticationManager.authenticate(new TokenAuthentication(token.asText()));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        if (authentication == null) {
            authentication = new TokenAuthentication(null);
            authentication.setAuthenticated(false);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
