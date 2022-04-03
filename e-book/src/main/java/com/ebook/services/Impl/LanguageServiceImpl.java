package com.ebook.services.Impl;

import com.ebook.entities.Language;
import com.ebook.repositories.LanguageRepository;
import com.ebook.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository repository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Language> getAllLanguages() {
        return repository.findAll() ;
    }

    @Override
    public Language getLanguageById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Language saveLanguage(Language language) {
        return repository.save(language);
    }

    @Override
    public void deleteLanguage(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Language updateLanguage(Language language) {
        return repository.save(language);
    }
}
