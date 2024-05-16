package com.example.leetscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leetscode.model.TestCase;

public interface TestCaseRepository  extends JpaRepository<TestCase, Long>{

    
}