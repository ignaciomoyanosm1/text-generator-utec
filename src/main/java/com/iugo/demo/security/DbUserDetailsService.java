package com.iugo.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iugo.demo.user.entity.User;
import com.iugo.demo.user.repository.UserRepository;

@Service
public class DbUserDetailsService implements UserDetailsService {

   private final UserRepository userRepo;

   public DbUserDetailsService(UserRepository userRepo) {
      this.userRepo = userRepo;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User u = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

      var authorities = u.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();

      return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPasswordHash(), authorities);
   }

}
