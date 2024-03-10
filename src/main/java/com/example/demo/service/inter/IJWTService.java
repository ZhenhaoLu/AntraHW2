package com.example.demo.service.inter;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface IJWTService {
    String generateToken(String username);
    String extractUsername(String token);
    Date extractExpiration(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    Claims extractAllClaims(String token);
    Boolean validateToken(String token, UserDetails userDetails);
    Boolean isTokenExpired(String token);
}
