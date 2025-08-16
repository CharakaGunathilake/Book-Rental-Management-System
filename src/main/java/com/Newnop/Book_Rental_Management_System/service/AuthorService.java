package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.AuthorRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AuthorResponseDto;

import java.util.List;

public interface AuthorService {
    AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto);

    AuthorResponseDto updateAuthor(Long Id, AuthorRequestDto authorRequestDto);

    void deleteAuthor(Long authorId);

    AuthorResponseDto getAuthorById(Long authorId);

    List<AuthorResponseDto> getAllAuthors();
}
