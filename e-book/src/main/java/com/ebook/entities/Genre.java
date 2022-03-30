package com.ebook.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
public class Genre{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "books_genres",
//            joinColumns = @JoinColumn(name = "genre_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<Book> books;
}
