package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.AuthorRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AuthorResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Author;
import com.Newnop.Book_Rental_Management_System.exception.custom.AuthorServiceException;
import com.Newnop.Book_Rental_Management_System.repository.AuthorRepository;
import com.Newnop.Book_Rental_Management_System.service.AuthorService;
import com.Newnop.Book_Rental_Management_System.utils.author_utils.AuthorMapper;
import com.Newnop.Book_Rental_Management_System.utils.common_utils.ValidationUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    @Override
    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        // Map DTO to entity (manual or via mapper)
        Author author = AuthorMapper.toEntity(authorRequestDto);
        Author savedAuthor;
        try {
            // Save entity
            savedAuthor = authorRepository.save(author);
        } catch (DataAccessException e) {
            // Handles DB-specific errors (e.g., constraint violations, duplicates)
            log.error("Database error while creating author: {}", e.getMessage(), e);
            throw new AuthorServiceException("Database error while creating author", e);

        }
        log.info("Author with ID {} created successfully", savedAuthor.getId());
        // Map to response DTO
        return AuthorMapper.toResponseDto(savedAuthor);
    }


    @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto authorRequestDto) {
        // Validate ID
        ValidationUtils.validateId("Author", id);

        // Find existing author
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + id));

        // Update fields
        AuthorMapper.updateEntity(authorRequestDto, existingAuthor);

        Author updatedAuthor;
        try {
            // Save updated entity
            updatedAuthor = authorRepository.save(existingAuthor);
        } catch (DataAccessException e) {
            log.error("Database error while updating author with ID {}: {}", id, e.getMessage(), e);
            throw new AuthorServiceException("Database error while updating author", e);
        }

        log.info("Author with ID {} updated successfully", updatedAuthor.getId());

        // Map to response DTO
        return AuthorMapper.toResponseDto(updatedAuthor);
    }


    @Override
    public void deleteAuthor(Long authorId) {
        // Validate ID
        ValidationUtils.validateId("Author", authorId);
        // Check if author exists
        if (!authorRepository.existsById(authorId)) {
            log.error("Author with ID {} not found for deletion", authorId);
            throw new EntityNotFoundException("Author not found with ID: " + authorId);
        }
        try {
            // Save updated entity
            authorRepository.deleteById(authorId);
            log.info("Author with ID {} deleted successfully", authorId);
        } catch (DataAccessException e) {
            log.error("Database error while deleting author: {}", e.getMessage(), e);
            throw new AuthorServiceException("Database error while deleting author", e);
        }
    }

    @Override
    public AuthorResponseDto getAuthorById(Long authorId) {
        // Validate ID
        ValidationUtils.validateId("Author", authorId);
        // Find existing author
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with ID: " + authorId));
        // Map to response DTO
        log.info("Author with ID {} retrieved successfully", authorId);
        return AuthorMapper.toResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        // Retrieve all authors
        List<Author> authors = authorRepository.findAll();

        // Check if authors list is empty
        if (authors.isEmpty()) {
            log.warn("No authors found");
            return List.of(); // Return empty list if no authors found
        }

        // Map to response DTOs
        List<AuthorResponseDto> authorResponseDtos = authors.stream()
                .map(AuthorMapper::toResponseDto)
                .toList();
        log.info("Retrieved {} authors successfully", authorResponseDtos.size());
        return authorResponseDtos;
    }
}
