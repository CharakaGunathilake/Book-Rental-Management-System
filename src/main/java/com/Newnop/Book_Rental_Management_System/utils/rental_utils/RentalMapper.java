package com.Newnop.Book_Rental_Management_System.utils.rental_utils;

import com.Newnop.Book_Rental_Management_System.dto.request.RentalRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.RentalResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Book;
import com.Newnop.Book_Rental_Management_System.entity.Rental;
import com.Newnop.Book_Rental_Management_System.entity.User;
import com.Newnop.Book_Rental_Management_System.enums.RentalStatus;
import com.Newnop.Book_Rental_Management_System.utils.book_utils.BookMapper;
import com.Newnop.Book_Rental_Management_System.utils.user_utils.UserMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class RentalMapper {

    private RentalMapper() {
        // Private constructor to prevent instantiation
    }

    // Method to map DTOs to entities
    public static Rental toEntity(RentalRequestDto rentalDto, Book book, User user) {
        return Rental.builder()
                .book(book)
                .user(user)
                .rentalStatus(RentalStatus.ACTIVE)
                .rentedDate(LocalDate.now())// Set the current date as the rented date
                .expectedReturnDate(CalculationUtils.calculateReturnDate(rentalDto.getExpectedReturnDate())) // Calculate expected return date
                .totalAmount(CalculationUtils.calculateTotalAmount(rentalDto.getExpectedReturnDate())) // Calculate total amount based on expected return date
                .build();
    }

    // Method to map entity to DTO
    public static RentalResponseDto toDto(Rental rental) {
        return RentalResponseDto.builder()
                .id(rental.getId())
                .book(BookMapper.toDto(rental.getBook()))
                .user(UserMapper.toDto(rental.getUser()))
                .rentedDate(rental.getRentedDate())
                .expectedReturnDate(rental.getExpectedReturnDate())
                .actualReturnDate(rental.getActualReturnDate())
                .rentalStatus(rental.getRentalStatus())
                .totalAmount(rental.getTotalAmount())
                .build();
    }



}
