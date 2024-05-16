package com.example.leetscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leetscode.model.Language;
import com.example.leetscode.repository.LanguageRepository;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    // LanguageService sẽ chứa các phương thức để thao tác với Language
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Optional<Language> getLanguageById(Long id) {
        return languageRepository.findById(id);
    }

    public Language addLanguage(Language language) {
        return languageRepository.save(language);
    }

    public void updateLanguage(Long id, Language language) {
        Language languageToUpdate = languageRepository.findById(id).get();
        languageToUpdate.setName(language.getName());
        languageRepository.save(languageToUpdate);
    }

    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }

    public void addAllLanguages(List<Language> languages) {
        languageRepository.saveAll(languages);
    }
    
}
