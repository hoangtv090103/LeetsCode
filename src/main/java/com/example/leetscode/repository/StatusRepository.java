package com.example.leetscode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leetscode.model.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
    @SuppressWarnings("null")
    Optional<Status> findById(Long id);

    Status findByDescription(String description);
}
