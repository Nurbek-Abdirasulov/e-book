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
    public Genre getGenreById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return repository.save(genre);
    }
}
