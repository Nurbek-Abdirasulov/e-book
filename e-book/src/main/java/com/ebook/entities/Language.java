package com.ebook.entities;

import lombok.*;

import javax.persistence.*;

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
}
