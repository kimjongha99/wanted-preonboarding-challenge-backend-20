package com.example.wantedchallenge.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    @Value("${secret-key}")
    private String secretKey;

    public String create(Long userId) { // userId를 Long 타입으로 변경
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    }

    public Long validate(String jwt) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
            return Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createRefreshToken(Long userId) {
        Date expiredDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    }

    public Long validateRefreshToken(String refreshToken) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();
            return Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}