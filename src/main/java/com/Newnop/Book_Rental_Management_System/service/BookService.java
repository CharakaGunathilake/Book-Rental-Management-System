package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.BookRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    BookResponseDto addBook(BookRequestDto bookRequestDto);

    BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto);

    BookResponseDto updateBookAvailabilityStatus(Long bookId, String status);

    BookResponseDto updateBookQuality(Long bookId, String quality);

    void deleteBook(Long bookId);

    BookResponseDto getBookById(Long bookId);

    BookResponseDto getBookByName(String bookName);

    List<BookResponseDto> getAllBooksByAuthorId(Long authorId);

    List<BookResponseDto> getAllBooksByGenreId(Long genreId);

    List<BookResponseDto> getAllBooksByAvailabilityStatus(String status);

    List<BookResponseDto> getAllBooksByQuality(String quality);

    List<BookResponseDto> getAllBooksByPublishedYear(Integer year);

    List<BookResponseDto> getAllBooks();
}
