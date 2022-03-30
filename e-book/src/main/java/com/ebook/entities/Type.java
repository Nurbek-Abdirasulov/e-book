package com.ebook.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "types")
@AllArgsConstructor
@NoArgsConstructor
public class Type{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "books_types",
//            joinColumns =@JoinColumn(name = "type_id") ,
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<Book> books;
}
