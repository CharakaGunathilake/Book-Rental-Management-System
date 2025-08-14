package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.RentalRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.RentalResponseDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RentalService {
    void addRental(RentalRequestDto rentalRequestDto);

    void updateRental(RentalRequestDto rentalRequestDto);

    void deleteRental(Long rentalId);

    void returnBook(Long rentalId);

    void cancelRental(Long rentalId);

    BigDecimal calculateRentalFee(Long rentalId);

    RentalResponseDto getRentalById(Long rentalId);

    RentalResponseDto getRentalByBookId(Long bookId);

    RentalResponseDto getRentalByUserId(Long userId);

    List<RentalResponseDto> getAllRentalsByBookId(Long BookId);

    List<RentalResponseDto> getAllRentalsByUserId(Long userId);

    List<RentalResponseDto> getAllRentalsByStatus(String status);

    List<RentalResponseDto> getAllRentalsByRentedDate(LocalDate rentedDate);

    List<RentalResponseDto> getAllRentalsByReturnDate(LocalDate returnDate);

    List<RentalResponseDto> getAllRentals();
}
