package ru.bugmakers.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Created by Ivan
 */
@Service
public class TokenAuthenticationManager implements AuthenticationManager, TokenData {

    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("userSecurityDetailService")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
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

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) {

        String token = authentication.getToken();
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(TOKEN_KEY).parse(token).getBody();
        } catch (Exception e) {
            throw new AuthenticationServiceException("Некорректный токен");
        }

        //проверяем время жизни токена
        Instant expirationInstant = claims.getExpiration().toInstant();
        if (expirationInstant.isAfter(Instant.now())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(claims.get(USERNAME, String.class));
            if (userDetails.isEnabled()) {
                return new TokenAuthentication(authentication.getToken(), userDetails.getAuthorities(), true, userDetails);
            } else {
                throw new AuthenticationServiceException("Пользователь заблокирован");
            }
        } else {
            throw new AuthenticationServiceException("Время жизни токена истекло");
        }

    }
}
