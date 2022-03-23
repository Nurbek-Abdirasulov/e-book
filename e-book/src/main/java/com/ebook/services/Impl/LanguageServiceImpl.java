package com.ebook.services.Impl;

import com.ebook.entities.Language;
import com.ebook.repositories.LanguageRepository;
import com.ebook.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> getNamesOfLanguagesToList() {
        List<Language> languages= repository.findAll();
        List<String> stringList = languages.stream()
                .map(language -> language.getName()).collect(Collectors.toList());
        return stringList;
    }

    @Override
    public Language getLanguageByName(String name) {
        return repository.getLanguageByName(name);
    }

    @Override
    public Language getLanguageById(Long id) {
        return repository.findById(id).get();
    }
}
