package com.ebook.controllers;

import com.ebook.entities.Book;
import com.ebook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@CrossOrigin("*")
public class BookController {
    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public ResponseEntity<List<Book>> getTodos() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping(
            path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> saveBook(@RequestParam("name") String name,
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
           return new ResponseEntity<>(bookService.saveBook(name, publisher, author,
                                                        aboutBook, fragments,
                                                        date, volume, quantity,
                                                        price, discount,
                                                        bestseller, file), HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping(value = "{id}/file/download")
    public byte[] downloadBookFile(@PathVariable("id") Long id) {
        return bookService.downloadBookFile(id);
    }
}

