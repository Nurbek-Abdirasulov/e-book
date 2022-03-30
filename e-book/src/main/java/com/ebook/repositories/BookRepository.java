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
    Book findByName(String name);


    Book getById(AddBookIdRequest id);
//  @Query(value = "select * from users where id in (select user_id from users_roles where role_id in (select id from roles where name = :role)) order by id", nativeQuery = true)

    @Query(value = "select * from books where id in (select book_id from books_types where type_id in (select id from types where id =  :type_id)) order by id", nativeQuery = true)
    List <Book> findAllByType(@Param("type_id") Long type_id);

}
