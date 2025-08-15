package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.AuthorRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AuthorResponseDto;
import com.Newnop.Book_Rental_Management_System.repository.AuthorRepository;
import com.Newnop.Book_Rental_Management_System.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final ObjectMapper mapper;
    private final AuthorRepository authorRepository;


    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        return null;
    }

    @Override
    public AuthorResponseDto updateAuthor(AuthorRequestDto authorRequestDto) {
        return null;
    }

    @Override
    public void deleteAuthor(Long authorId) {

    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        return null;
    }

    @Override
    public AuthorResponseDto getAuthorByBookId(Long bookId) {
        return null;
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return List.of();
    }
}
