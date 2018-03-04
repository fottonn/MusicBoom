package ru.bugmakers.config.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.bugmakers.config.principal.UserPrincipal;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static ru.bugmakers.config.jwt.TokenData.*;

/**
 * Created by Ivan
 */
@Component
public class TokenUtils {

    public static String generate(Authentication authentication) {

        if (authentication == null) return null;

        Map<String, Object> claims = new HashMap<>();
        Instant tokenCreateTime = Instant.now();
        claims.put(USERNAME, authentication.getName());
        claims.put(USER_ID, ((UserPrincipal) authentication.getPrincipal()).getUser().getId());
        claims.put(TOKEN_CREATE_DATE, tokenCreateTime.toEpochMilli());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(claims);
        jwtBuilder.setExpiration(Date.from(tokenCreateTime.plusSeconds(TOKEN_LIFE_TIME)));
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, TOKEN_KEY).compact();
    }

    public static String getUserName(String token) {
        return getClaims(token).get(USERNAME, String.class);
    }

    public static Instant getExpiration(String token) {
        return getClaims(token).getExpiration().toInstant();
    }

    public static Long getUserId(String token) {
        return getClaims(token).get(USER_ID, Long.class);
    }

    private static DefaultClaims getClaims(String token) {
        if (token == null) return null;
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(TOKEN_KEY).parse(token).getBody();
        } catch (Exception e) {
            throw new AuthenticationServiceException("Некорректный токен");
        }
        return claims;
    }
}
