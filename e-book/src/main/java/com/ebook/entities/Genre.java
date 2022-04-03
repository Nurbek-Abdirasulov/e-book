package com.ebook.entities;

import lombok.*;

import javax.persistence.*;

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

}
