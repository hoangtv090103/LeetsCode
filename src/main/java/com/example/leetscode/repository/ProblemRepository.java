package com.example.leetscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leetscode.model.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long>{
}
