package com.dotto.app.handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Component
public class JwtHandler {

    private final String type = "Bearer ";

    public String createToken(String key, Map<String, Object> privateClaims, Long maxAgeSeconds){
        Date now = new Date();
        return Jwts.builder()
                .addClaims(privateClaims)
                .addClaims(Map.of(Claims.ISSUED_AT, now, Claims.EXPIRATION, new Date(now.getTime() + maxAgeSeconds * 1000L)))
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();

    }

    public Optional<Claims> parse (String key, String token){
        try{
            return Optional.of(Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(unType(token)).getBody());
        }catch (JwtException e){
            return Optional.empty();
        }
    }


    private String unType(String token){
        return token.substring(type.length());
    }


}
