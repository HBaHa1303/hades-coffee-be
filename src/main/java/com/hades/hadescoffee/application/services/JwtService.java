package com.hades.hadescoffee.application.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secretKey}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .expiration(Date.from(Instant.now().plusSeconds(expiration)))
                .signWith(getKey())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(Claims claims) {
        return claims.getSubject();
    }

    public boolean isTokenValid(Claims claims, UserDetails userDetails) {
        return claims.getSubject().equals(userDetails.getUsername())
                && !claims.getExpiration().before(new Date());
    }


    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
