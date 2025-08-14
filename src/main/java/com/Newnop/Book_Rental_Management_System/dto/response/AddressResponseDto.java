package com.Newnop.Book_Rental_Management_System.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String postalCode;
}
