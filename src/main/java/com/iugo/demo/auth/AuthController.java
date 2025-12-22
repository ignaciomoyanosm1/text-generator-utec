package com.iugo.demo.auth;

import com.iugo.demo.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

      var auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.username(), req.password())
      );

      List<String> roles = auth.getAuthorities().stream()
                               .map(GrantedAuthority::getAuthority) // "ROLE_ADMIN"
                               .toList();

      String token = jwtService.generateToken(auth.getName(), roles);
      return new LoginResponse(token);
   }
}
