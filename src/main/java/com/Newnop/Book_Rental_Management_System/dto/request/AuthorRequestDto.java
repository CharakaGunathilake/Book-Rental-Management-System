package com.Newnop.Book_Rental_Management_System.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {
    @NotNull(message = "Author name cannot be null")
    @Pattern(regexp = "^[a-zA-Z.\\s]+$", message = "Author name must contain only letters, spaces and dots")
    private String name;
    private String biography;
}
