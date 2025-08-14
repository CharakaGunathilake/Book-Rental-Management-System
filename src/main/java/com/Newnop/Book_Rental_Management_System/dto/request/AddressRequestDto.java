package com.Newnop.Book_Rental_Management_System.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    @NotNull(message = "Address line 1 cannot be null")
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    @NotNull(message = "City cannot be null")
    private String city;
    private String postalCode;
}
