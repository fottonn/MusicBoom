package ru.bugmakers.config.jwt.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.bugmakers.config.jwt.TokenAuthentication;
import ru.bugmakers.config.jwt.TokenData;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Ivan
 */
public class TokenAuthenticationCheckFilter extends GenericFilterBean implements TokenData {

    private AuthenticationManager authenticationManager;

    public TokenAuthenticationCheckFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {

        final HttpServletRequest rq = (HttpServletRequest) request;

        Authentication authentication = null;
        try {
            BufferedReader reader = rq.getReader();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(reader);
            reader.close();

            JsonNode token = null;
            if (jsonNode != null) token = jsonNode.get(TOKEN_NAME);
            if (token != null) {
                authentication = authenticationManager.authenticate(new TokenAuthentication(token.asText()));
            }
        } catch (Exception ignored) {
        }

        if (authentication == null) {
            authentication = new TokenAuthentication(null);
            authentication.setAuthenticated(false);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);

    }

}
