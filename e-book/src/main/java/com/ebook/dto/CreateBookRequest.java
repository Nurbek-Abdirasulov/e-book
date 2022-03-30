package com.ebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBookRequest {
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
}
