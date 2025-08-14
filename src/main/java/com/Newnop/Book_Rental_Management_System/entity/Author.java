package com.Newnop.Book_Rental_Management_System.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author extends PersistedObject{
    @Column(nullable = false)
    private String name;
    private String biography;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
