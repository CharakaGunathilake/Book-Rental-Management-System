package com.Newnop.Book_Rental_Management_System.utils.book_utils;

import com.Newnop.Book_Rental_Management_System.dto.request.BookRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AuthorResponseDto;
import com.Newnop.Book_Rental_Management_System.dto.response.BookResponseDto;
import com.Newnop.Book_Rental_Management_System.dto.response.GenreResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Author;
import com.Newnop.Book_Rental_Management_System.entity.Book;
import com.Newnop.Book_Rental_Management_System.entity.Genre;
import com.Newnop.Book_Rental_Management_System.enums.AvailabilityStatus;
import com.Newnop.Book_Rental_Management_System.enums.BookQuality;

import java.util.UUID;

public class BookMapper {

    private BookMapper() {
        // Private constructor to prevent instantiation
    }

    public static Book toEntity(BookRequestDto dto, Author author, Genre genre) {
        return Book.builder()
                .title(dto.getTitle())
                .summary(dto.getSummary())
                .author(author)
                .genre(genre)
                .bookCode(UUID.randomUUID().toString())
                .language(dto.getLanguage())
                .availabilityStatus(AvailabilityStatus.AVAILABLE) // Set default availability status
                .publishedYear(dto.getPublishedYear())
                .bookQuality(BookQuality.valueOf(dto.getBookQuality()))
                .build();
    }

    public static BookResponseDto toDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .summary(book.getSummary())
                .bookCode(book.getBookCode())
                .author(new AuthorResponseDto(book.getAuthor().getId(), book.getAuthor().getName(), null))
                .genre(new GenreResponseDto(book.getGenre().getId(), book.getGenre().getName()))
                .availabilityStatus(book.getAvailabilityStatus())
                .language(book.getLanguage())
                .publishedYear(book.getPublishedYear())
                .bookQuality(book.getBookQuality())
                .build();
    }

    public static void updateEntity(BookRequestDto dto, Book book) {
        book.setTitle(dto.getTitle());
        book.setSummary(dto.getSummary());
        book.setLanguage(dto.getLanguage());
        book.setPublishedYear(dto.getPublishedYear());
        book.setBookQuality(BookQuality.valueOf(dto.getBookQuality()));
    }
}

