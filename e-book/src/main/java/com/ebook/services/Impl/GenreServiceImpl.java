package com.ebook.services.Impl;

import com.ebook.entities.Genre;
import com.ebook.repositories.GenreRepository;
import com.ebook.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository repository;

    @Autowired
    public GenreServiceImpl(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Genre> getAllGenres() {
        return repository.findAll();
    }

    @Override
    public List<String> getNamesOfGenresToList() {
        List<Genre> genres = repository.findAll();
        List<String> stringList=genres.stream()
                .map(genre-> genre.getName()).collect(Collectors.toList());
        return stringList;
    }

    @Override
    public Genre getGenreByName(String name) {
        return repository.getGenreByName(name);
    }

    @Override
    public Genre getGenreById(Long id) {
        return repository.findById(id).get();
    }
}
