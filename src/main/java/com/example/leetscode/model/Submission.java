package com.example.leetscode.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private String sourceCode;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    private String stdin;

    private String token;

    private LocalDate sumbittedAt;
    private LocalDate updatedAt;

    public Submission() {
    }

    public Submission(User user, Problem problem, String code, Language language, Status status, String token) {
        this.user = user;
        this.problem = problem;
        this.sourceCode = code;
        this.language = language;
        this.status = status;
        this.sumbittedAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Problem getProblem() {
        return problem;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public Language getLanguage() {
        return language;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getSumbittedAt() {
        return sumbittedAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setStatus(Status status) {
        this.status = status;
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
                ", user=" + user +
                ", problemId=" + problem.getId() +
                ", code='" + sourceCode + '\'' +
                ", language=" + language +
                ", status=" + status +
                ", sumbittedAt=" + sumbittedAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
