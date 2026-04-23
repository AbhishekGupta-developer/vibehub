package com.myorganisation.vibehub.utility;

import com.myorganisation.vibehub.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET;
    private final SecretKey KEY;
    private final long AUTH_EXPIRATION;

    public JwtUtil(@Value("${jwt.secret}") String jwtSecret) {
        SECRET = jwtSecret;
        KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
        AUTH_EXPIRATION = 1000 * 60 * 2; // 2 mins
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUserName())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + AUTH_EXPIRATION))
                .signWith(KEY, Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

//    public boolean validateToken(String username, UserDetails userDetails, String token) {
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return getClaims(token).getExpiration().before(new Date());
//    }


}
