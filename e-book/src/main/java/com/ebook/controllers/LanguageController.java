package com.ebook.controllers;

import com.ebook.entities.Language;
import com.ebook.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/language")
@CrossOrigin
public class LanguageController {
    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Language>> getAllLanguages(){
        return new ResponseEntity<>(languageService.getAllLanguages(), HttpStatus.OK);
    }

    @DeleteMapping("/{languageId}")
    @CrossOrigin
    public String deleteLanguage(@PathVariable("languageId") Long languageId){
        languageService.deleteLanguage(languageId);
        return "Language with ID = " + languageId + " was successfully deleted.";
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Language> addLanguage(@RequestBody Language language){
        return new ResponseEntity<>(languageService.saveLanguage(language), HttpStatus.OK);
    }

    @PutMapping
    @CrossOrigin
    public ResponseEntity<Language> updateLanguage(@RequestBody Language language){
        return new ResponseEntity<>(languageService.updateLanguage(language), HttpStatus.OK);
    }
    @GetMapping("/{languageId}")
    public ResponseEntity<Language> getLanguageById(@PathVariable("languageId") Long languageId){
        return new ResponseEntity<>(languageService.getLanguageById(languageId), HttpStatus.OK);
    }
}
