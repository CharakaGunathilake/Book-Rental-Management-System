package com.Newnop.Book_Rental_Management_System.dto.response;

import com.Newnop.Book_Rental_Management_System.enums.UserRole;
import com.Newnop.Book_Rental_Management_System.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phoneNumber;
    private AddressResponseDto address;
    private UserStatus userStatus;
    private UserRole userRole;
}
