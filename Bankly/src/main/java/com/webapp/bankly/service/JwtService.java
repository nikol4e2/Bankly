package com.webapp.bankly.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private static final long EXPIRATION_TIME = 60 * 60 * 24 ;

    @Value("${jwtSecret}")
    private String jwtSecret;

    public SecretKey generateKey(){
        byte[] keyBytes = jwtSecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public String generateToken(String username){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(generateKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public String extractSubject(String token)
    {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token)
    {
        return new Date().before(extractExpiration(token));

    }

    public Date extractExpiration(String token)
    {
        return extractClaims(token).getExpiration();
    }



}
