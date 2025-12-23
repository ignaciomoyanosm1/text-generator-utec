package com.iugo.demo.auth;

import com.iugo.demo.security.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

   private final AuthenticationManager authManager;
   private final JwtService jwtService;

   public AuthController(AuthenticationManager authManager, JwtService jwtService) {
      this.authManager = authManager;
      this.jwtService = jwtService;
   }

   @PostMapping("/login")
   public LoginResponse login(@RequestBody LoginRequest req) {

       log.info("Login attempt username={}", req.username());

       var auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.username(), req.password())
      );

      List<String> roles = auth.getAuthorities().stream()
                               .map(GrantedAuthority::getAuthority)
                               .toList();

      log.info("Login success username={} roles={}", auth.getName(), roles);
      String token = jwtService.generateToken(auth.getName(), roles);
      return new LoginResponse(token);
   }
}
