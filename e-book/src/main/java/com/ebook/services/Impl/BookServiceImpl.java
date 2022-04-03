package com.ebook.services.Impl;

import com.ebook.bucket.BucketName;
import com.ebook.dto.CreateBookRequest;
import com.ebook.entities.Book;
import com.ebook.repositories.BookRepository;
import com.ebook.services.BookService;
import com.ebook.services.FilesBooksStore.FileBooksStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
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
    public Book create(CreateBookRequest request) {
        Book book = Book.builder()
                .name(request.getName())
                .publisher(request.getPublisher())
                .author(request.getAuthor())
                .aboutBook(request.getAboutBook())
                .fragment(request.getFragment())
                .date(request.getDate())
                .volume(request.getVolume())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .discount(request.getDiscount())
                .bestseller(request.getBestseller())
                .build();
        return repository.save(book);
    }

    @Override
    public void uploadBookFile(Long bookId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = getBookPath(bookId);
        String fileName = String.format("%s", file.getOriginalFilename());

        try {
            fileBooksStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
    }

    @Override
    public byte[] downloadBookFile(Long bookId) {
        Book book = getById(bookId);
        if (book == null) {
            throw new IllegalStateException(String.format("Book %s not found", bookId));
        }
        String path = getBookPath(book.getId());

        return book.getBookLink()
                .map(key -> fileBooksStore.download(path, key))
                .orElse(new byte[0]);
    }

    private String getBookPath(Long id) {
        return String.format("%s/%s", BucketName.INFORMATION.getBucketName(), id);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public @Nullable Book getById(Long bookId) {
        return repository.findById(bookId).orElse(null);
    }

    @Override
    public Book replace(Book book) {
        return repository.save(book);
    }

    @Override
    public List<Book> findAllByType(Long type_id) {
        return repository.findAllByType(type_id);
    }

    @Override
    public List<Book> findAllByLanguage(Long language_id) {
        return repository.findAllByLanguage(language_id);
    }

    @Override
    public List<Book> findAllByGenre(Long genre_id) {
        return repository.findAllByGenres(genre_id);
    }

    @Override
    public List<Book> getBookByName(String nameFragment) {
        return repository.getBookByName(nameFragment);
    }
}
