package com.ebook.controllers;

import com.ebook.dto.CreateBookRequest;
import com.ebook.dto.PatchBookRequest;
import com.ebook.entities.Book;
import com.ebook.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(CreateBookRequest request) {
        try {
            Book saved = bookService.create(request);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<Book>> getAllBooksByType(@PathVariable Long typeId){
        try{
            return new ResponseEntity<>(bookService.findAllByType(typeId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/language/{languageId}")
    public ResponseEntity<List<Book>> getAllBooksByLanguage(@PathVariable Long languageId){
        try{
            return new ResponseEntity<>(bookService.findAllByLanguage(languageId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<Book>> getAllByGenre(@PathVariable Long genreId){
        try{
            return new ResponseEntity<>(bookService.findAllByGenre(genreId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{bookName}")
    public ResponseEntity<List<Book>> getBooksByName(@PathVariable String bookName){
        try{
            return new ResponseEntity<>(bookService.getBookByName(bookName), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
            path = "/{bookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        try {
            Book book = bookService.getById(bookId);
            if (book == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(book);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(
            path = "/{bookId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> patchBook(@PathVariable Long bookId,
                                      @RequestBody PatchBookRequest patchRequest) {
        try{
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
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{bookId}/file")
    public byte[] downloadBookFile(@PathVariable("bookId") Long id) {
        try{
            return bookService.downloadBookFile(id);
        }catch (Exception e){
            System.out.println("Error with method downloadBookFile() - " + e);
            return new byte[0];
        }
    }

    @PostMapping(value = "/{bookId}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadBookFile(@PathVariable("bookId") Long id, @RequestParam("file") MultipartFile file) {
        try{
            bookService.uploadBookFile(id, file);
        }catch (Exception e){
            System.out.println("Error with method uploadBookFile() - " + e);
        }
    }
}

