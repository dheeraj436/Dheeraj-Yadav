package org.demo.springsecurityandjwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    private String SECRET_KEY = "mysecretkeymysecretkeymysecretkeymysecretkey12345";

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpire(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generate JWT token for the user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claim = new HashMap<>();
        return createToken(claim, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claim, String subject) {
        return Jwts.builder().claims(claim).subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public boolean validateToken(String jwt, UserDetails userDetails) {
        String username = extractUserName(jwt);
        return username.equals(userDetails.getUsername()) && !isTokenExpire(jwt);
    }
}
