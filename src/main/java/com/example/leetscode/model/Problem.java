package com.example.leetscode.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

enum Difficulty {
    EASY,
    MEDIUM,
    HARD
}

@Entity
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    // @Enumerated(EnumType.STRING) is used to specify that the enum should be
    // persisted as a string. If you don't use this annotation, the enum will be
    // persisted as an ordinal value
    private Difficulty difficulty;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    @ElementCollection
    private Set<String> tags;
    
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TestCase> testCases;

    public Problem() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }
    
    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public LocalDate getUpdatedAt() {
        return this.updatedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDate.now();
    }

    public Set<String> getTags() {
        return this.tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<TestCase> getTestCases() {
        return this.testCases;
    }

    public void setTestCases(Set<TestCase> testCases) {
        this.testCases = testCases;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", difficulty=" + difficulty +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
