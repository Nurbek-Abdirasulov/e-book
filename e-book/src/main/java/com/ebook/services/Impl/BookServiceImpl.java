package com.ebook.services.Impl;

import com.ebook.bucket.BucketName;
import com.ebook.entities.AddBookIdRequest;
import com.ebook.entities.Book;
import com.ebook.repositories.BookRepository;
import com.ebook.services.BookService;
import com.ebook.services.FilesBooksStore.FileBooksStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


@Service
public class BookServiceImpl implements BookService {
    private final FileBooksStore fileBooksStore;
    private final BookRepository repository;
    @Autowired
    public BookServiceImpl(FileBooksStore fileBooksStore, BookRepository bookRepository) {
        this.fileBooksStore = fileBooksStore;
        this.repository = bookRepository;
    }

    @Override
    public Book saveBook(String name, String publisher, String author,
                         String aboutBook, String fragments, int date,
                         int volume, int quantity, int price, int discount,
                         boolean bestseller, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = String.format("%s/%s", BucketName.INFORMATION.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileBooksStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        Book book= Book.builder()
                .name(name)
                .publisher(publisher)
                .author(author)
                .aboutBook(aboutBook)
                .fragment(fragments)
                .date(date)
                .volume(volume)
                .quantity(quantity)
                .price(price)
                .discount(discount)
                .bestseller(bestseller)
                .build();
        repository.save(book);
        return repository.findByName(book.getName());
    }


    @Override
    public byte[] downloadBookFile(Long id) {
        Book book=repository.findById(id).get();
        String path = String.format("%s/%s", BucketName.INFORMATION.getBucketName(),
                book.getId());
        return book.getBookLink()
                .map(key-> fileBooksStore.download(path, key))
                        .orElse(new byte[0]);
    }


    @Override
    public List<Book> getAllBooks() {
        List<Book> books= new ArrayList<>();
        repository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book AddBookIdRequest(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Book getById(Long id) {
        return repository.findById(id).get();
    }


    @Override
    public Book save(Book book) {
        return repository.save(book);
    }
}
