package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.BookRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.BookResponseDto;
import com.Newnop.Book_Rental_Management_System.repository.BookRepository;
import com.Newnop.Book_Rental_Management_System.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void addBook(BookRequestDto bookRequestDto) {

    }

    @Override
    public void updateBook(BookRequestDto bookRequestDto) {

    }

    @Override
    public void deleteBook(Long bookId) {

    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        return null;
    }

    @Override
    public BookResponseDto getBookByName(String bookName) {
        return null;
    }

    @Override
    public List<BookResponseDto> getAllBooksByAuthorName(String authorName) {
        return List.of();
    }

    @Override
    public List<BookResponseDto> getAllBooksByAuthorId(String authorId) {
        return List.of();
    }

    @Override
    public List<BookResponseDto> getAllBooksByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<BookResponseDto> getAllBooksByGenreId(Long genreId) {
        return List.of();
    }

    @Override
    public List<BookResponseDto> getAllBooksByAvailabilityStatus(String status) {
        return List.of();
    }

    @Override
    public List<BookResponseDto> getAllBooksByQuality(String quality) {
        return List.of();
    }

    @Override
    public List<BookResponseDto> getAllBooksByPublishedYear(Integer year) {
        return List.of();
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return List.of();
    }
}
