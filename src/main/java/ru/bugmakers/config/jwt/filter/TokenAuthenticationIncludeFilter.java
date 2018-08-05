package ru.bugmakers.config.jwt.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.bugmakers.config.jwt.TokenUtils;
import ru.bugmakers.enums.Role;
import ru.bugmakers.enums.RsStatus;
import ru.bugmakers.localpers.entity.WhiteToken;
import ru.bugmakers.localpers.service.WhiteTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static ru.bugmakers.config.jwt.TokenData.TOKEN_NAME;

/**
 * Created by Ivan
 * <p>
 * Добавляет токен в ответ, если пользователь авторизовался
 */
public class TokenAuthenticationIncludeFilter extends GenericFilterBean {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private WhiteTokenService whiteTokenService;

    @Autowired
    public void setWhiteTokenService(WhiteTokenService whiteTokenService) {
        this.whiteTokenService = whiteTokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String resultContent;
        CapturingResponseWrapper capturingResponseWrapper = new CapturingResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, capturingResponseWrapper);
        final String responseContent = capturingResponseWrapper.getCaptureAsString();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getName() != null && isAuthContainsRole(authentication)) {
            JsonNode rootNode = OBJECT_MAPPER.readTree(responseContent);
            if (rootNode != null && rootNode.path("status").isValueNode() && rootNode.path("status").textValue().equals(RsStatus.SUCCESS.name())) {
                JsonNode tokenNode = rootNode.path(TOKEN_NAME);
                if (tokenNode.isValueNode()) {
                    ((ObjectNode) rootNode).remove(TOKEN_NAME);
                }
                final String token = TokenUtils.generate(authentication);
                ((ObjectNode) rootNode).put(TOKEN_NAME, token);
                resultContent = rootNode.toString();
                whiteTokenService.saveWhiteToken(new WhiteToken(TokenUtils.getUserId(token), token));
            } else {
                resultContent = responseContent;
            }
        } else {
            resultContent = responseContent;
        }
        response.getWriter().write(resultContent);
    }

    private boolean isAuthContainsRole(Authentication authentication) {
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority auth : authorities) {
            if (Role.getRoles().contains(auth.getAuthority())) return true;
        }
        return false;
    }
}
