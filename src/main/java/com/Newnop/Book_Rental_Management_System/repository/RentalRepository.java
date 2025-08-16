package com.Newnop.Book_Rental_Management_System.repository;

import com.Newnop.Book_Rental_Management_System.entity.Rental;
import com.Newnop.Book_Rental_Management_System.enums.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findAllByBookId(Long bookId);

    List<Rental> findAllByUserId(Long userId);

    List<Rental> findAllByRentalStatus(RentalStatus rentalStatus);

    List<Rental> findAllByRentedDate(LocalDate rentedDate);

    List<Rental> findAllByExpectedReturnDate(LocalDate returnDate);
}               
