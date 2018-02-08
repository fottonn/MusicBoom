package ru.bugmakers.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.bugmakers.config.principal.UserPrincipal;
import ru.bugmakers.localpers.WhiteTokenService;

import java.time.Instant;

/**
 * Created by Ivan
 */
public class TokenAuthenticationProvider implements AuthenticationProvider, TokenData {

    private UserDetailsService userDetailsService;
    private WhiteTokenService whiteTokenService;

    public TokenAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setWhiteTokenService(WhiteTokenService whiteTokenService) {
        this.whiteTokenService = whiteTokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (authentication instanceof TokenAuthentication) {
                return processAuthentication((TokenAuthentication) authentication);
            } else {
                authentication.setAuthenticated(false);
                return authentication;
            }
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationServiceException("Ошибка аутентификации", e);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.isAssignableFrom(authentication);
    }

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) {

        String token = authentication.getToken();

        //проверяем время жизни токена
        if (TokenUtils.getExpiration(token).isBefore(Instant.now())) {
            throw new AuthenticationServiceException("Время жизни токена истекло");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(TokenUtils.getUserName(token));
        //Проверяем не заблокирован ли пользователь
        if (!userDetails.isEnabled()) {
            throw new AuthenticationServiceException("Пользователь заблокирован");
        }
        //Проверяем токен в белом списке
        Long userId = ((UserPrincipal) userDetails).getUser().getId();
        if (!token.equals(whiteTokenService.findWhiteTokenByUserId(userId))) {
            throw new AuthenticationServiceException("Токена нет в белом списке");
        }
        return new TokenAuthentication(authentication.getToken(), userDetails.getAuthorities(), true, userDetails);

    }

}
