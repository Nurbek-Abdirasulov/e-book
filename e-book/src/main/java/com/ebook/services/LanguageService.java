package com.ebook.services;

import com.ebook.entities.Language;
import com.ebook.entities.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LanguageService {
    List<Language> getAllLanguages();

    Language getLanguageById(Long id);

    Language saveLanguage(Language language);

    void deleteLanguage(Long id);

    Language updateLanguage(Language language);
}
