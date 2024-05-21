package com.example.leetscode.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long problemId;

    private String token;

    private LocalDate sumbittedAt;
    private LocalDate updatedAt;

    public Submission() {
    }

    public Submission(Long userId, Long problemId, String token) {
        this.userId = userId;
        this.problemId = problemId;

        this.token = token;
        this.sumbittedAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public LocalDate getSumbittedAt() {
        return sumbittedAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDate.now();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", userId=" + userId +
                ", problemId=" + problemId +
                ", sumbittedAt=" + sumbittedAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
