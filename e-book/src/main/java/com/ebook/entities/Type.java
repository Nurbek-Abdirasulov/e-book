package com.ebook.entities;

import lombok.*;

import javax.persistence.*;

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
}
