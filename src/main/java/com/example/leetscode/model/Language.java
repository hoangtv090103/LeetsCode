package com.example.leetscode.model;

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
    // Trong trường hợp này, GenerationType.IDENTITY được sử dụng để tự động tạo giá trị cho khóa chính dựa trên cơ chế tự tăng của cơ sở dữ liệu.
    private Long id;
    private String name;
    

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public Long getJudge0Id() {
    //     return judge0Id;
    // }

    // public void setJudge0Id(Long judge0Id) {
    //     this.judge0Id = judge0Id;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
