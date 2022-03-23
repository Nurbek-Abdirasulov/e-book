package com.ebook.services;


import com.ebook.entities.AddBookIdRequest;
import com.ebook.entities.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Service
public interface BookService {
    Book saveBook(String name,String publisher,
            String author, String aboutBook,
            String fragments, int date,
            int volume, int quantity, int price,
            int discount, boolean bestseller,  MultipartFile file);

    byte[] downloadBookFile(Long id);
    List<Book> getAllBooks();
    Book AddBookIdRequest(Long id);
    Book getById(Long id);

    Book save(Book book);


}


