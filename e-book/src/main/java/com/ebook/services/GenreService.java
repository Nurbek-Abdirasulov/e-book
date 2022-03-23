package com.ebook.services;

import com.ebook.entities.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GenreService {
    List<Genre> getAllGenres();
    List<String> getNamesOfGenresToList();
    Genre getGenreByName(String name);
    Genre getGenreById(Long id);
}
