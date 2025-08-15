package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.GenreRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.GenreResponseDto;
import com.Newnop.Book_Rental_Management_System.repository.GenreRepository;
import com.Newnop.Book_Rental_Management_System.service.GenreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final ObjectMapper objectMapper;


    @Override
    public GenreResponseDto addGenre(GenreRequestDto genreRequestDto) {
        return null;
    }

    @Override
    public GenreResponseDto updateGenre(GenreRequestDto genreRequestDto) {
        return null;
    }

    @Override
    public void deleteGenre(Long genreId) {

    }

    @Override
    public List<GenreResponseDto> getAllGenres() {
        return List.of();
    }
}
