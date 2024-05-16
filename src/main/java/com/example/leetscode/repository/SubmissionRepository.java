package com.example.leetscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leetscode.model.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{

}
