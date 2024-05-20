package com.example.leetscode.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    public LocalDate createdAt;
    public LocalDate updatedAt;

    public Comment() {
    }

    public Comment(User user, Problem problem, String content) {
        this.user = user;
        this.problem = problem;
        this.content = content;
        this.createdAt = LocalDate.now();
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

    public String getContent() {
        return content;
    }

    public Comment getParent() {
        return parent;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", user=" + user + ", problem=" + problem + ", content='" + content + '\''
                + ", parent=" + parent + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
