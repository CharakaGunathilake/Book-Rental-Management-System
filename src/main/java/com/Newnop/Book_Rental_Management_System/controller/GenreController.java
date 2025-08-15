package com.Newnop.Book_Rental_Management_System.controller;

import com.Newnop.Book_Rental_Management_System.dto.request.GenreRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.GenreResponseDto;
import com.Newnop.Book_Rental_Management_System.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genre")
public class GenreController {
    // This class handles genre-related endpoints.
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreResponseDto> addGenre(@Valid @RequestBody GenreRequestDto genreRequestDto) {
        log.info("Add new Genre attempt");
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.addGenre(genreRequestDto));
    }

    @PutMapping
    public ResponseEntity<GenreResponseDto> updateGenre(@Valid @RequestBody GenreRequestDto genreRequestDto) {
        log.info("Update existing Genre attempt");
        return ResponseEntity.ok(genreService.updateGenre(genreRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteGenre(@RequestParam Long id) {
        log.info("Delete existing Genre attempt");
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("all")
    public ResponseEntity<List<GenreResponseDto>> getAllGenres() {
        log.info("Retrieve all Genres attempt");
        return ResponseEntity.ok(genreService.getAllGenres());
    }
}
