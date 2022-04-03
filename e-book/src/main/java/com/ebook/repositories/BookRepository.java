package com.ebook.repositories;

import com.ebook.dto.AddBookIdRequest;
import com.ebook.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book getById(AddBookIdRequest id);

    @Query(value = "select * from books where id in" +
            " (select book_id from books_types where type_id"+
            " in (select id from types where id =  :type_id))"+
            " order by id", nativeQuery = true)
    List <Book> findAllByType(@Param("type_id") Long type_id);

    @Query(value = "select * from books where id in"+
            " (select book_id from books_languages where language_id"+
            " in (select id from languages where id = :language_id))"+
            " order by id", nativeQuery = true)
    List<Book> findAllByLanguage(@Param("language_id") Long language_id);

    @Query(value = "select * from books where id in"+
            " (select book_id from books_genres where genre_id"+
            " in (select id from genres where id = :genre_id))"+
            " order by id", nativeQuery = true)
    List<Book> findAllByGenres(@Param("genre_id") Long genre_id);

    @Query(value = "select * from books where name = :nameFragment", nativeQuery = true)
    List<Book> getBookByName(String nameFragment);

}
