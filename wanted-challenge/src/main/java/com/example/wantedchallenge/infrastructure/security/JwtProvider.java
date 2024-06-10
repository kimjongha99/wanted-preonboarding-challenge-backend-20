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

    // userId를 받아와서 JWT 토큰을 생성
    public String create(String userId) {
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS)); // 만료기간은 한 시간으로 설정
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // JWT 생성
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    }

    // JWT 검증
    public String validate(String jwt) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            return claims.getSubject();
        } catch (Exception exception) {
            log.error("JWT validation error: {}", exception.getMessage());
            return null;
        }
    }

    // Refresh token 생성
    public String createRefreshToken(String userId) {
        Date expiredDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS)); // 7일 만료
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
    }

    // Refresh token 검증
    public String validateRefreshToken(String refreshToken) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            return claims.getSubject();
        } catch (Exception exception) {
            log.error("Refresh token validation error: {}", exception.getMessage());
            return null;
        }
    }
}
