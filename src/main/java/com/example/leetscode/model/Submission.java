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

enum Status {
    ACCEPTED,
    WRONG_ANSWER,
    TIME_LIMIT_EXCEEDED,
    MEMORY_LIMIT_EXCEEDED,
    RUNTIME_ERROR,
    COMPILATION_ERROR
}

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

    private String code;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate sumbittedAt;
    private LocalDate updatedAt;


    public Submission() {
    }

    public Submission(User user, Problem problem, String code, Language language) {
        this.user = user;
        this.problem = problem;
        this.code = code;
        this.language = language;
        this.status = Status.WRONG_ANSWER;
        this.sumbittedAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
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

    public String getCode() {
        return code;
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

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", user=" + user +
                ", problemId=" + problem.getId() +
                ", code='" + code + '\'' +
                ", language=" + language +
                ", status=" + status +
                ", sumbittedAt=" + sumbittedAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
