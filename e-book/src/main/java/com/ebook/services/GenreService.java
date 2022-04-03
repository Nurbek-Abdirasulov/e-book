package com.ebook.services;

import com.ebook.entities.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface GenreService {
    List<Genre> getAllGenres();

    Genre getGenreById(Long id);

    Genre saveGenre(Genre genre);

    void deleteGenre(Long id);

    Genre updateGenre(Genre genre);
}
