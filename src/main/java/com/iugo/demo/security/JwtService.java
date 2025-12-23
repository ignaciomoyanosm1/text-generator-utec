package com.iugo.demo.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

   private final Key key;

   private final long expirationMs;

   public JwtService(@Value("${security.jwt.secret}") String secret, @Value("${security.jwt.expiration-minutes}") long expMinutes) {
      this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
      this.expirationMs = expMinutes * 60_000;
   }

   public String generateToken(String username, List<String> roles) {
      Date now = new Date();
      Date exp = new Date(now.getTime() + expirationMs);

      return Jwts.builder().setSubject(username).claim("roles", roles) // ["ROLE_ADMIN", ...]
                 .setIssuedAt(now).setExpiration(exp).signWith(key, SignatureAlgorithm.HS256).compact();
   }

   public Jws<Claims> parse(String token) {
      return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
   }

}
