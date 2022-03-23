package com.ebook.controllers;

import com.ebook.entities.AddBookIdRequest;
import com.ebook.entities.Book;
import com.ebook.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1")
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
    public ResponseEntity<List<Book>> getTodos() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }


    @PostMapping(
            path = "/books",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> saveBook(@RequestParam("bookId") Long bookId,
                                         @RequestParam("name") String name,
                                         @RequestParam("publisher")String publisher,
                                         @RequestParam("author")String author,
                                         @RequestParam("aboutBook")String aboutBook,
                                         @RequestParam("fragments")String fragments,
                                         @RequestParam("date")int date,
                                         @RequestParam("volume")int volume,
                                         @RequestParam("quantity")int quantity,
                                         @RequestParam("price")int price,
                                         @RequestParam("discount")int discount,
                                         @RequestParam("bestseller")boolean bestseller,
                                         @RequestParam("file")MultipartFile file) {
        
       try{
           bookService.saveBook(name, publisher, author,
               aboutBook, fragments,
               date, volume, quantity,
               price, discount,
               bestseller, file);
           return new ResponseEntity<>(bookService.getById(bookId), HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PostMapping(value = "/users/{userId}/books/{languageId}/{typeId}/{genreId}")
    public ResponseEntity<Book> updateBook(@PathVariable("userId") Long userId,
                                           @PathVariable("languageId") Long languageId,
                                           @PathVariable("typeId") Long typeId,
                                           @PathVariable("genreId") Long genreId,
                                           @RequestBody AddBookIdRequest bookId){
            Book books =bookService.AddBookIdRequest(bookId.getBookId());
            books.getGenres().add(genreService.getGenreById(genreId));
            books.getLanguages().add(languageService.getLanguageById(languageId));
            books.getTypes().add(typeService.getTypeById(typeId));
            books.getUsers().add(userService.getUserById(userId));
            return new ResponseEntity<>(bookService.save(books), HttpStatus.OK);
        }


    @GetMapping(value = "{id}/file/download")
    public byte[] downloadBookFile(@PathVariable("id") Long id) {
        return bookService.downloadBookFile(id);
    }
}

