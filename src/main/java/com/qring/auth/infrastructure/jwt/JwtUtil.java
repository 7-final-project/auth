package com.qring.auth.infrastructure.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    @Value("${service.jwt.secret-key}")
    private String secretKey;

    private static Key key;
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public static String createToken(Long userId) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .claim("userId", userId)
                        .setExpiration(new Date(date.getTime() + (60 * 60 * 1000L)))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    public static String createPassport(Long id, String username, String role, String slackEmail) {
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .claim("userId", id)
                        .claim("role", role)
                        .claim("slackEmail", slackEmail)
                        .setExpiration(new Date(date.getTime() + (5 * 60 * 1000L)))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }
}
