package com.ebook.controllers;


import com.ebook.entities.Genre;
import com.ebook.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre/")
@CrossOrigin
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<Genre>> getAllGenres(){
        try{
            return new ResponseEntity<>(genreService.getAllGenres(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{genreId}")
    @CrossOrigin
    public String deleteGenre(@PathVariable("genreId") Long genreId){
        try {
            genreService.deleteGenre(genreId);
            return "Genre with ID = " + genreId + " was successfully deleted.";
        }catch (Exception e){
            return String.valueOf(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre){
        try{
            return new ResponseEntity<>(genreService.saveGenre(genre), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @CrossOrigin
    public ResponseEntity<Genre> updateGenre(@RequestBody Genre genre){
        try {
            return new ResponseEntity<>(genreService.updateGenre(genre), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{genreId}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("genreId") Long genreId){
        try{
            return new ResponseEntity<>(genreService.getGenreById(genreId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
