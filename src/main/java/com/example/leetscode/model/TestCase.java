package com.example.leetscode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_cases")
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;
    
    @Column(columnDefinition = "TEXT")
    private String input;
    
    @Column(columnDefinition = "TEXT")
    private String expectedOutput;
    
    private Boolean isSample;

    // Getters and setters
    public Long getId() {
        return this.id;
    }

    public Problem getProblem() {
        return this.problem;
    }

    public String getInput() {
        return this.input;
    }

    public String getExpectedOutput() {
        return this.expectedOutput;
    }

    public Boolean getIsSample() {
        return this.isSample;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public void setIsSample(Boolean isSample) {
        this.isSample = isSample;
    }
}
