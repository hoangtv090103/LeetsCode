package com.example.leetscode.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
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

    private Long userId;

    private Long problemId;

    private String sourceCode;

    // @ManyToOne
    // @JoinColumn(name = "languageId")
    // private Language language;

    private Long languageId;

    // @ManyToOne
    // @JoinColumn(name = "statusId")
    // private Status status;

    private Long statusId;

    private String stdin;

    private String stdout;

    private String stderr;

    private String token;

    private LocalDate sumbittedAt;
    private LocalDate updatedAt;

    public Submission() {
    }

    public Submission(Long userId, Long problemId, String sourceCode, Long languageId, Long statusId, String stdin,
            String stdout, String stderr, String token) {
        this.userId = userId;
        this.problemId = problemId;
        this.sourceCode = sourceCode;
        this.languageId = languageId;
        this.statusId = statusId;
        this.sumbittedAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
        this.stdin = stdin;
        this.stdout = stdout;
        this.stderr = stderr;
        this.token = token;
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

    public String getSourceCode() {
        return sourceCode;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public LocalDate getSumbittedAt() {
        return sumbittedAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setSourceCode(String code) {
        this.sourceCode = code;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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

    public String getStdin() {
        return stdin;
    }

    public void setStdin(String stdin) {
        this.stdin = stdin;
    }

    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", userId=" + userId +
                ", problemId=" + problemId +
                ", code='" + sourceCode + '\'' +
                ", languageId=" + languageId +
                ", statusId=" + statusId +
                ", sumbittedAt=" + sumbittedAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
