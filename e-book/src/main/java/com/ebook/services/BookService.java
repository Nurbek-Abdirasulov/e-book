package com.ebook.services;


import com.ebook.dto.CreateBookRequest;
import com.ebook.entities.Book;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.List;

@Service
public interface BookService {
    Book create(CreateBookRequest request);

    byte[] downloadBookFile(Long bookId);

    void uploadBookFile(Long bookId, MultipartFile file);

    List<Book> getAllBooks();

    Book getById(Long bookId);

    Book replace(Book book);

    List <Book> findAllByType(Long type_id);
}


