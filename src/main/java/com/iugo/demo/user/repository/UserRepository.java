package com.iugo.demo.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iugo.demo.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);

}
