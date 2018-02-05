package ru.bugmakers.config.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ivan
 */
@Component
public class TokenGenerator implements TokenData {

    public static String generate(String username) {

        if (username == null) return null;

        Map<String, Object> claims = new HashMap<>();
        Instant tokenCreateTime = Instant.now();
        claims.put(USERNAME, username);
        claims.put(TOKEN_CREATE_DATE, tokenCreateTime.toEpochMilli());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(claims);
        jwtBuilder.setExpiration(Date.from(tokenCreateTime.plusSeconds(TOKEN_LIFE_TIME)));
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, TOKEN_KEY).compact();
    }
}
