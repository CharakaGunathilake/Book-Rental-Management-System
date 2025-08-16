package com.Newnop.Book_Rental_Management_System.utils.author_utils;

import com.Newnop.Book_Rental_Management_System.dto.request.AuthorRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AuthorResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Author;

public class AuthorMapper {

    private AuthorMapper() {
        // Private constructor to prevent instantiation
    }

    // Method to convert AuthorRequestDto to Author entity
    public static Author toEntity(AuthorRequestDto authorRequestDto) {
        return Author.builder()
                .name(authorRequestDto.getName())
                .biography(authorRequestDto.getBiography())
                .build();
    }

    // Method to convert Author entity to AuthorResponseDto
    public static AuthorResponseDto toResponseDto(Author author) {
        return AuthorResponseDto.builder()
                .id(author.getId())
                .name(author.getName())
                .biography(author.getBiography())
                .build();
    }

    // Method to update Author entity from AuthorRequestDto
    public static void updateEntity(AuthorRequestDto authorRequestDto, Author author) {
        author.setName(authorRequestDto.getName());
        author.setBiography(authorRequestDto.getBiography());
    }
}
