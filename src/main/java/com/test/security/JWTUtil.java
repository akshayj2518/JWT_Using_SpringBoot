package com.test.security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private final String SECRET_KEY = "3tFrl5mAPvhEC5wL2cIj+5+EkzR5qA9s5+6uCQkm1uE="; // store securely!

   public String generateToken(String username){
       return Jwts.builder()
               .setSubject(username)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
               .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()),SignatureAlgorithm.HS256)
               .compact();
   }

   public String extractUsername(String token){
       return Jwts.parserBuilder()
               .setSigningKey(SECRET_KEY.getBytes())
               .build()
               .parseClaimsJws(token)
               .getBody()
               .getSubject();
   }

   public boolean validateToken(String token, UserDetails userDetails){
       final String username = extractUsername(token);
       return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
   }

   public boolean isTokenExpired(String token){
       Date expiry = Jwts.parserBuilder()
               .setSigningKey(SECRET_KEY.getBytes())
               .build()
               .parseClaimsJws(token)
               .getBody()
               .getExpiration();

       return expiry.before(new Date());
   }
}
