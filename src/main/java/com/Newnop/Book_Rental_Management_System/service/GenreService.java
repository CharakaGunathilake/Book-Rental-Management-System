package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.GenreRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.GenreResponseDto;

import java.util.List;

public interface GenreService {
    GenreResponseDto addGenre(GenreRequestDto genreRequestDto);

    GenreResponseDto updateGenre(Long id, GenreRequestDto genreRequestDto);

    void deleteGenre(Long genreId);

    List<GenreResponseDto> getAllGenres();
}
