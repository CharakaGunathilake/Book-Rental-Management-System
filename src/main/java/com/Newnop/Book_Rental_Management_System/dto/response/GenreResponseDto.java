package com.Newnop.Book_Rental_Management_System.dto.response;

import com.Newnop.Book_Rental_Management_System.enums.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreResponseDto {
    private Long id;
    private String name;
}
