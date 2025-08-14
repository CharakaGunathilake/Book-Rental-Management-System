package com.Newnop.Book_Rental_Management_System.dto.response;

import com.Newnop.Book_Rental_Management_System.enums.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDto {
    private Long id;
    private BookResponseDto book;
    private UserResponseDto user;
    private LocalDate rentedDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;
    private RentalStatus rentalStatus;
    private BigDecimal totalAmount;
}
