package com.example.leetscode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.leetscode.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @SuppressWarnings("null")
    Optional<User> findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByToken(String token);
}