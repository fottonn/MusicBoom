package ru.bugmakers.config.jwt.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import ru.bugmakers.config.jwt.TokenAuthentication;
import ru.bugmakers.config.jwt.TokenData;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Ivan
 *
 * Проверяет токен
 */
public class TokenAuthenticationCheckFilter extends AbstractAuthenticationProcessingFilter implements TokenData {

    public TokenAuthenticationCheckFilter() {
        super("/**");
        //если аутентификация успешная, устанавливаем контекст и форвардим к сервлету
        setAuthenticationSuccessHandler(((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getRequestDispatcher(request.getServletPath() + request.getPathInfo()).forward(request, response);
        }));
        //если аутентификация неуспешная, то редиректим на страницу логина
        setAuthenticationFailureHandler(((request, response, exception) -> response.sendRedirect("/")));

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            BufferedReader reader = request.getReader();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(reader);
            reader.close();
            String token = null;

            if (jsonNode != null) token = jsonNode.get(TOKEN_NAME).asText();
            if (token == null) throw new AuthenticationServiceException("Отсутствует токен");

            TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
            return getAuthenticationManager().authenticate(tokenAuthentication);
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationServiceException("Ошибка аутентификации", e);
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        super.doFilter(req, res, chain);
    }
}
