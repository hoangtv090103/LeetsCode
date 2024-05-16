package com.example.leetscode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leetscode.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Long>{
    Optional<Language> findById(Long id);
    Language findByName(String name);
}
