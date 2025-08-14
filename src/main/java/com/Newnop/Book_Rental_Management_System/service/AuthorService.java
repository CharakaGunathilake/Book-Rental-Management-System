package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.AuthorRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    void addAuthor(AuthorRequestDto authorRequestDto);

    void updateAuthor(AuthorRequestDto authorRequestDto);

    void deleteAuthor(Long authorId);

    AuthorResponseDto getAuthorById(Long authorId);

    AuthorResponseDto getAuthorByName(String authorName);

    AuthorResponseDto getAuthorByBookName(String bookName);

    AuthorResponseDto getAuthorByBookId(Long bookId);

    List<AuthorResponseDto> getAllAuthors();
}
