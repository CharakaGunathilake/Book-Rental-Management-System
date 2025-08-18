package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.GenreRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.GenreResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Genre;
import com.Newnop.Book_Rental_Management_System.exception.custom.GenreServiceException;
import com.Newnop.Book_Rental_Management_System.repository.GenreRepository;
import com.Newnop.Book_Rental_Management_System.service.GenreService;
import com.Newnop.Book_Rental_Management_System.utils.common_utils.ValidationUtils;
import com.Newnop.Book_Rental_Management_System.utils.genre_utils.GenreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;


    @Override
    public GenreResponseDto addGenre(GenreRequestDto genreRequestDto) {
        try {
            // Map DTO to entity
            Genre genre = GenreMapper.toEntity(genreRequestDto);

            // Save entity
            Genre savedGenre = genreRepository.save(genre);

            log.info("Genre with ID {} created successfully", savedGenre.getId());
            // Map to response DTO
            return GenreMapper.toResponseDto(savedGenre);

        } catch (Exception e) {
            // Handle exceptions appropriately
            throw new GenreServiceException("Error while adding genre", e);
        }
    }

    @Override
    public GenreResponseDto updateGenre(Long id, GenreRequestDto genreRequestDto) {
        try {
            // Validate the genreRequestDto
            ValidationUtils.validateId("Genre", id);

            // Map DTO to entity
            Genre genre = GenreMapper.toEntity(genreRequestDto);

            // Save entity
            Genre updatedGenre = genreRepository.save(genre);

            log.info("Genre with ID {} updated successfully", updatedGenre.getId());
            // Map to response DTO
            return GenreMapper.toResponseDto(updatedGenre);

        } catch (Exception e) {
            log.error("Error while updating genre with ID {}: {}", id, e.getMessage(), e);
            throw new GenreServiceException("Error while updating genre", e);
        }
    }

    @Override
    public void deleteGenre(Long genreId) {
        try {
            // Validate the genreId
            ValidationUtils.validateId("Genre", genreId);

            // Check if the genre exists
            if (!genreRepository.existsById(genreId)) {
                log.warn("Genre with ID {} not found", genreId);
                throw new GenreServiceException("Genre not found with ID: " + genreId);
            }

            // Delete the genre
            genreRepository.deleteById(genreId);
            log.info("Genre with ID {} deleted successfully", genreId);

        } catch (Exception e) {
            log.error("Error while deleting genre with ID {}: {}", genreId, e.getMessage(), e);
            throw new GenreServiceException("Error while deleting genre", e);
        }
    }

    @Override
    public List<GenreResponseDto> getAllGenres() {
        // Fetch all genres
        List<Genre> genres = genreRepository.findAll();

        if (genres.isEmpty()) {
            log.info("No genres found");
            return List.of(); // Return an empty list if no genres are found
        }

        log.info("Fetched {} genres successfully", genres.size());
        // Map to response DTOs
        return genres.stream()
                .map(GenreMapper::toResponseDto)
                .toList();
    }
}
