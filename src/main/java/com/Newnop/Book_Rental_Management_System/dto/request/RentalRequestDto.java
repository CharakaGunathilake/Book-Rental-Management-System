package com.Newnop.Book_Rental_Management_System.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalRequestDto {
    @NotNull(message = "Book ID cannot be null")
    private Long bookId;
    @NotNull(message = "User ID cannot be null")
    private Long userId;
    @NotNull(message = "Return date cannot be null")
    private LocalDate expectedReturnDate;
}
