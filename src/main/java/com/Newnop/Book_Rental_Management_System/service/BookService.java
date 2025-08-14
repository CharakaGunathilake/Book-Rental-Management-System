package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.BookRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.BookResponseDto;

import java.util.List;

public interface BookService {
    void addBook(BookRequestDto bookRequestDto);

    void updateBook(BookRequestDto bookRequestDto);

    void deleteBook(Long bookId);

    BookResponseDto getBookById(Long bookId);

    BookResponseDto getBookByName(String bookName);

    List<BookResponseDto> getAllBooksByAuthorName(String authorName);

    List<BookResponseDto> getAllBooksByAuthorId(String authorId);

    List<BookResponseDto> getAllBooksByGenre(String genre);

    List<BookResponseDto> getAllBooksByGenreId(Long genreId);

    List<BookResponseDto> getAllBooksByAvailabilityStatus(String status);

    List<BookResponseDto> getAllBooksByQuality(String quality);

    List<BookResponseDto> getAllBooksByPublishedYear(Integer year);

    List<BookResponseDto> getAllBooks();
}
