package com.ebook.services;

import com.ebook.entities.Language;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LanguageService {
    List<Language> getAllLanguages();
    List<String> getNamesOfLanguagesToList();
    Language getLanguageByName(String name);
    Language getLanguageById(Long id);

}
