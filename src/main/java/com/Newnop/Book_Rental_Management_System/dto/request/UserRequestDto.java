package com.Newnop.Book_Rental_Management_System.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @NotNull(message = "First name cannot be null")
    private String firstname;
    @NotNull(message = "Last name cannot be null")
    private String lastname;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;
    private AddressRequestDto address;
    @NotNull(message = "User role cannot be null")
    private String userRole;
}
