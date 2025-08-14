package com.Newnop.Book_Rental_Management_System.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genre extends PersistedObject{
    @Column(nullable = false, unique = true)
    private String name;
    private String code;
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
