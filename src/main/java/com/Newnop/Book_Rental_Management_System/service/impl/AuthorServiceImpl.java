package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.AuthorRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AuthorResponseDto;
import com.Newnop.Book_Rental_Management_System.repository.AuthorRepository;
import com.Newnop.Book_Rental_Management_System.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final ObjectMapper mapper;
    private final AuthorRepository authorRepository;

    @Override
    public void addAuthor(AuthorRequestDto authorRequestDto) {

    }

    @Override
    public void updateAuthor(AuthorRequestDto authorRequestDto) {

    }

    @Override
    public void deleteAuthor(Long authorId) {

    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        return null;
    }

    @Override
    public AuthorResponseDto getAuthorByName(String authorName) {
        return null;
    }

    @Override
    public AuthorResponseDto getAuthorByBookName(String bookName) {
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
