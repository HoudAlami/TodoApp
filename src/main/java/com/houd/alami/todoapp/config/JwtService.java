package com.houd.alami.todoapp.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "123456"; // Clé secrète utilisée pour signer le token

    public String generateToken(String pseudo) {
        return Jwts.builder()
                .setSubject(pseudo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 heure de validité
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Extraction du nom d'utilisateur
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // Utilise le ClaimsResolver pour extraire le sujet
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token); // Extraire le nom d'utilisateur
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Vérifie si le token est expiré
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extraction des claims du token
    private <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.resolve(claims);
    }

    // Extraction de toutes les claims du token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extraction de la date d'expiration
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @FunctionalInterface
    interface ClaimsResolver<T> {
        T resolve(Claims claims);
    }
}
