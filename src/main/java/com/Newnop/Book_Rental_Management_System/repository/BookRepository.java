package com.Newnop.Book_Rental_Management_System.repository;

import com.Newnop.Book_Rental_Management_System.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
