package com.ebook.entities;

import javax.persistence.*;


@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendorname")
    private String vendorname;

    @Column(name = "password")
    private String password;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "number")
    private int number;

}
