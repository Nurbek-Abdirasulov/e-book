package com.ebook.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "books_languages",
//            joinColumns = @JoinColumn(name = "language_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<Book> books;
}
