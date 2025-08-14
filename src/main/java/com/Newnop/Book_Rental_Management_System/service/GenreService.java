package com.Newnop.Book_Rental_Management_System.service;

import com.Newnop.Book_Rental_Management_System.dto.request.GenreRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.GenreResponseDto;

import java.util.List;

public interface GenreService {
    void addGenre(GenreRequestDto genreRequestDto);

    void updateGenre(GenreRequestDto genreRequestDto);

    void deleteGenre(Long genreId);

    GenreResponseDto getGenreById(Long genreId);

    GenreResponseDto getGenreByName(String genreName);

    List<GenreResponseDto> getAllGenres();
}
