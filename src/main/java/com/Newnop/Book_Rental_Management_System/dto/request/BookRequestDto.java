package com.Newnop.Book_Rental_Management_System.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    @NotNull(message = "Title cannot be null")
    private String title;
    private String summary;
    @NotNull(message = "Author Id cannot be null")
    private Long authorId;
    @NotNull(message = "Genre Id cannot be null")
    private Long genreId;
    @NotNull(message = "Availability Status cannot be null")
    private String language;
    @NotNull(message = "Published Year cannot be null")
    private Integer publishedYear;
    @NotNull(message = "Book Quality cannot be null")
    private String bookQuality;
}
