package com.smartbiz.security;

import com.smartbiz.entity.ENUM.AdminRole;
import com.smartbiz.entity.ENUM.BussinessRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email, BussinessRole bussinessRole, AdminRole adminRole, Long businessId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("bussinessRole", bussinessRole.name())
                .claim("adminRole", adminRole.name())
                .claim("businessId", businessId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public String extractBussinessRole(String token) {
        return getClaims(token).get("bussinessRole", String.class);
    }

    public String extractAdminRole(String token) {
        return getClaims(token).get("adminRole", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
