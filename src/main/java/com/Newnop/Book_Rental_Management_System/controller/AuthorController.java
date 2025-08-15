package com.Newnop.Book_Rental_Management_System.controller;

import com.Newnop.Book_Rental_Management_System.dto.request.AuthorRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.AuthorResponseDto;
import com.Newnop.Book_Rental_Management_System.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {
    // This class handles author-related endpoints.

    private final AuthorService authorService;


    //Endpoint for adding an Author
    @PostMapping
    public ResponseEntity<AuthorResponseDto> addAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        log.info("Add new Author attempt");
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.addAuthor(authorRequestDto));
    }

    //Endpoint for updating and Author
    @PutMapping
    public ResponseEntity<AuthorResponseDto> updateAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        log.info("Update existing Author attempt");
        return ResponseEntity.ok(authorService.updateAuthor(authorRequestDto));
    }

    //Endpoint to delete an author by id
    @DeleteMapping
    public ResponseEntity<?> deleteAuthor(@RequestParam Long id) {
        log.info("Delete existing Author attempt");
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();

    }

    //Endpoint to retrieve an author by id
    @GetMapping
    public ResponseEntity<AuthorResponseDto> getAuthorById(@RequestParam Long id) {
        log.info("Retrieve Author by Id attempt");
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    //Endpoint to retrieve an author by book id
    @GetMapping(value = "/by_book")
    public ResponseEntity<AuthorResponseDto> getAuthorByBookId(@RequestParam Long id){
        log.info("Retrieve Author by book id attempt");
        return ResponseEntity.ok(authorService.getAuthorByBookId(id));
    }

    //Endpoint to retrieve all authors
    @GetMapping("/all")
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthor() {
        log.info("Retrieve all Authors attempt");
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

}
