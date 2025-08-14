package com.Newnop.Book_Rental_Management_System.repository;

import com.Newnop.Book_Rental_Management_System.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}
