package com.hades.hadescoffee.application.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secretKey}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(Date.from(Instant.now().plusMillis(expiration)))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
