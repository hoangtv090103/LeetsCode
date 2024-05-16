package com.example.leetscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leetscode.model.Tag;

public interface TagRepository  extends JpaRepository<Tag, Long>{
    
}
