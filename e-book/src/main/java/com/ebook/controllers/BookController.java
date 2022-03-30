package com.ebook.controllers;

import com.ebook.dto.CreateBookRequest;
import com.ebook.dto.PatchBookRequest;
import com.ebook.entities.Book;
import com.ebook.entities.User;
import com.ebook.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/books")
@CrossOrigin("*")
public class BookController {
    private final BookService bookService;
    private final UserService userService;
    private final GenreService genreService;
    private final TypeService typeService;
    private final LanguageService languageService;

    @Autowired
    public BookController(BookService bookService, UserService userService, GenreService genreService, TypeService typeService, LanguageService languageService) {
        this.bookService = bookService;
        this.userService = userService;
        this.genreService = genreService;
        this.typeService = typeService;
        this.languageService = languageService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Book> create(CreateBookRequest request) {
        Book saved = bookService.create(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/types/{type_id}")
    public ResponseEntity<List<Book>> getAllBooksByType(@PathVariable Long type_id){
        return new ResponseEntity<>(bookService.findAllByType(type_id), HttpStatus.OK);
    }




//    @GetMapping("/users")
//    private ResponseEntity<List<User>> getAllUsers() {
//        try {
//            return new ResponseEntity<>(userService.findByRole("ROLE_USER"), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }



    @GetMapping(
            path = "/{bookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> get(@PathVariable Long bookId) {
        Book book = bookService.getById(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(book);
        }
    }

    @PatchMapping(
            path = "/{bookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Book> patch(@PathVariable Long bookId,
                                      @RequestBody PatchBookRequest patchRequest) {
        Book book = bookService.getById(bookId);
        if (patchRequest.getGenreId() != null) {
            book.getGenres().add(genreService.getGenreById(patchRequest.getGenreId()));
        }
        if (patchRequest.getLanguageId() != null) {
            book.getLanguages().add(languageService.getLanguageById(patchRequest.getLanguageId()));
        }
        if (patchRequest.getTypeId() != null) {
            book.getTypes().add(typeService.getTypeById(patchRequest.getTypeId()));
        }
        if (patchRequest.getUserId() != null) {
            book.getUsers().add(userService.getUserById(patchRequest.getUserId()));
        }
        Book patched = bookService.replace(book);
        return ResponseEntity.ok(patched);
    }

    @GetMapping(value = "/{bookId}/file")
    public byte[] downloadBookFile(@PathVariable("bookId") Long id) {
        return bookService.downloadBookFile(id);
    }

    @PostMapping(value = "/{bookId}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadBookFile(@PathVariable("bookId") Long id, @RequestParam("file") MultipartFile file) {
        bookService.uploadBookFile(id, file);
    }
}

