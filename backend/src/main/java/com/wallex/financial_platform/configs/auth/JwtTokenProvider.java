package com.wallex.financial_platform.configs.auth;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")  // Se obtiene de application.properties
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // ✅ Genera un token JWT con los datos del usuario
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Se almacena el email en el token
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Expira en X tiempo
                .signWith(SignatureAlgorithm.HS512, secretKey) // Firma el token con la clave secreta
                .compact();
    }

    // ✅ Obtiene el email del usuario desde el token
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // ✅ Valida si el token es correcto
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | SignatureException e) {
            return false;
        }
    }

    // ✅ Extrae un claim específico del token
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
