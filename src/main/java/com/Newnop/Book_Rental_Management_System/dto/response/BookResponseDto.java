package com.Newnop.Book_Rental_Management_System.dto.response;

import com.Newnop.Book_Rental_Management_System.enums.AvailabilityStatus;
import com.Newnop.Book_Rental_Management_System.enums.BookQuality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
    private Long id;
    private String title;
    private String summary;
    private String bookCode;
    private AuthorResponseDto author;
    private GenreResponseDto genre;
    private AvailabilityStatus availabilityStatus;
    private String language;
    private String publishedYear;
    private BookQuality bookQuality;
}
