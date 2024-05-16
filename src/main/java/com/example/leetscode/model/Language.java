package com.example.leetscode.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "languages")
public class Language {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue chỉ định cách sinh giá trị cho khóa chính.
    // Trong trường hợp này, GenerationType.IDENTITY được sử dụng để tự động tạo giá
    // trị cho khóa chính dựa trên cơ chế tự tăng của cơ sở dữ liệu.
    private Long id;
    private String name;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
