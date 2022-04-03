package com.ebook.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;


@Entity
@Data
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String publisher;
    private String author;
    private String aboutBook;
    private String fragment;
    private int date;
    private int volume;
    private int quantity;
    private int price;
    private int discount;
    private Boolean bestseller;
    private String bookLink;

    public Optional<String> getBookLink() {
        return Optional.ofNullable(bookLink);
    }

    public void setBookLink(String bookLink) {
        this.bookLink = bookLink;
    }

    public List<User> getUsers() {
        return users;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_languages",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<Language> languages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_types",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<Type> types;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return date == book.date &&
                volume == book.volume &&
                quantity == book.quantity &&
                price == book.price &&
                discount == book.discount &&
                id.equals(book.id) &&
                name.equals(book.name) &&
                publisher.equals(book.publisher) &&
                author.equals(book.author) &&
                aboutBook.equals(book.aboutBook) &&
                fragment.equals(book.fragment) &&
                bestseller.equals(book.bestseller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publisher, author, aboutBook, fragment, date, volume, quantity, price, discount, bestseller);
    }

}
