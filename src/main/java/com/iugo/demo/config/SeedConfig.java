package com.iugo.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.iugo.demo.user.entity.Role;
import com.iugo.demo.user.entity.User;
import com.iugo.demo.user.repository.UserRepository;
import com.iugo.demo.user.repository.RoleRepository;

@Configuration
@Profile({"dev","local"})
public class SeedConfig {

   @Bean
   CommandLineRunner seed(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder encoder) {
      return args -> {
         Role adminRole = roleRepo.findByName("ROLE_ADMIN")
                                  .orElseGet(() -> roleRepo.save(makeRole("ROLE_ADMIN")));
         Role userRole = roleRepo.findByName("ROLE_USER")
                                 .orElseGet(() -> roleRepo.save(makeRole("ROLE_USER")));

         userRepo.findByUsername("admin").orElseGet(() -> {
            User u = new User();
            u.setUsername("admin");
            u.setPasswordHash(encoder.encode("1234"));
            u.getRoles().add(adminRole);
            return userRepo.save(u);
         });

         userRepo.findByUsername("user").orElseGet(() -> {
            User u = new User();
            u.setUsername("user");
            u.setPasswordHash(encoder.encode("1234"));
            u.getRoles().add(userRole);
            return userRepo.save(u);
         });
      };
   }

   private Role makeRole(String name) {
      Role r = new Role();
      r.setName(name);
      return r;
   }
}
