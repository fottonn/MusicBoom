package ru.bugmakers.config.jwt.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.bugmakers.config.jwt.TokenData;
import ru.bugmakers.config.jwt.TokenGenerator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ivan
 */
public class TokenAuthenticationIncludeFilter extends GenericFilterBean implements TokenData {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CapturingResponseWrapper capturingResponseWrapper = new CapturingResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, capturingResponseWrapper);
        final String responseContent = capturingResponseWrapper.getCaptureAsString();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getName() != null) {
            String token = TokenGenerator.generate(authentication.getName());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(responseContent);
            JsonNode tokenNode = rootNode.path(TOKEN_NAME);
            if (!tokenNode.isMissingNode()) {
                ((ObjectNode) rootNode).remove(TOKEN_NAME);
            } else {
                ((ObjectNode) rootNode).put(TOKEN_NAME, token);
            }
            String responseContentWithToken = rootNode.toString();
            response.getWriter().write(responseContentWithToken);

        } else {
            response.getWriter().write(responseContent);
        }

    }
}
