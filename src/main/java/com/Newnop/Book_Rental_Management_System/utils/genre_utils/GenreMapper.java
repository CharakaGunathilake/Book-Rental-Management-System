package com.Newnop.Book_Rental_Management_System.utils.genre_utils;

import com.Newnop.Book_Rental_Management_System.dto.request.GenreRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.GenreResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Genre;

import java.util.UUID;

public class GenreMapper {
    private GenreMapper() {
        // Private constructor to prevent instantiation
    }


    public static Genre toEntity(GenreRequestDto genreRequestDto) {
        return Genre.builder()
                .name(genreRequestDto.getName())
                .build();
    }

    public static GenreResponseDto toResponseDto(Genre genre) {
        return GenreResponseDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
