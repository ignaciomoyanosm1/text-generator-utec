package com.iugo.demo.security;

import java.io.IOException;
import java.util.List;

import io.jsonwebtoken.Claims;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

   private final JwtService jwtService;

   public JwtAuthFilter(JwtService jwtService) {
      this.jwtService = jwtService;
   }

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
         throws ServletException, IOException {

      String header = request.getHeader("Authorization");
      if (header == null || !header.startsWith("Bearer ")) {
         chain.doFilter(request, response);
         return;
      }

      String token = header.substring(7);
      try {
         Claims claims = jwtService.parse(token).getBody();
         String username = claims.getSubject();

         @SuppressWarnings("unchecked")
         List<String> roles = claims.get("roles", List.class);

         var authorities = roles.stream()
                                .map(SimpleGrantedAuthority::new)
                                .toList();

         var auth = new UsernamePasswordAuthenticationToken(username, null, authorities);
         SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (Exception e) {
         SecurityContextHolder.clearContext();
      }

      chain.doFilter(request, response);
   }
}
