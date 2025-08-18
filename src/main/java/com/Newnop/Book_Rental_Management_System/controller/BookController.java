package com.Newnop.Book_Rental_Management_System.controller;

import com.Newnop.Book_Rental_Management_System.dto.request.BookRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.BookResponseDto;
import com.Newnop.Book_Rental_Management_System.service.BookService;
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
@RequestMapping("/api/v1/book")
public class BookController {
    // This class handles book-related endpoints.

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        log.info("Add new Book attempt");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(bookRequestDto));
    }

    @PutMapping
    public ResponseEntity<BookResponseDto> updateBook(@RequestParam Long id, @Valid @RequestBody BookRequestDto bookRequestDto) {
        log.info("Update existing Book by Id: {} attempt", id);
        return ResponseEntity.ok(bookService.updateBook(id, bookRequestDto));
    }

    @PatchMapping(params = "id,availability_status")
    public ResponseEntity<BookResponseDto> updateBookAvailabilityStatus(@RequestParam Long id, @RequestParam("availability_status") String availabilityStatus) {
        log.info("Update existing Book availability status by Id: {} attempt", id);
        return ResponseEntity.ok(bookService.updateBookAvailabilityStatus(id, availabilityStatus));
    }

    @PatchMapping(params = "id,quality")
    public ResponseEntity<BookResponseDto> updateBookQuality(@RequestParam Long id, @RequestParam String quality) {
        log.info("Update existing Book quality by Id: {} attempt", id);
        return ResponseEntity.ok(bookService.updateBookQuality(id, quality));
    }

    @DeleteMapping
    public ResponseEntity<BookResponseDto> deleteBookById(@RequestParam Long id) {
        log.info("Delete existing Book by Id: {} Attempt", id);
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "id")
    public ResponseEntity<BookResponseDto> getBookById(@RequestParam Long id) {
        log.info("Retrieve Book by id: {} attempt", id);
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping(params = "name")
    public ResponseEntity<BookResponseDto> getBookByName(@RequestParam String name) {
        log.info("Retrieve Book by name: {} attempt", name);
        return ResponseEntity.ok(bookService.getBookByName(name));
    }

    @GetMapping(params = "author")
    public ResponseEntity<List<BookResponseDto>> getAllBooksByAuthorId(@RequestParam("author") Long id) {
        log.info("Retrieve Book by author id: {} attempt", id);
        return ResponseEntity.ok(bookService.getAllBooksByAuthorId(id));
    }

    @GetMapping(params = "availability_status")
    public ResponseEntity<List<BookResponseDto>> getAllBooksByAvailabilityStatus(@RequestParam("availability_status") String status) {
        log.info("Retrieve all Books by availability status: {} attempt", status);
        return ResponseEntity.ok(bookService.getAllBooksByAvailabilityStatus(status));
    }

    @GetMapping(params = "quality")
    public ResponseEntity<List<BookResponseDto>> getAllBooksByQuality(@RequestParam String quality) {
        log.info("Retrieve all Books by quality: {} attempt", quality);
        return ResponseEntity.ok(bookService.getAllBooksByQuality(quality));
    }

    @GetMapping(params = "genre")
    public ResponseEntity<List<BookResponseDto>> getAllBooksGenreId(@RequestParam("genre") Long id) {
        log.info("Retrieve all Books by Genre_Id: {} attempt", id);
        return ResponseEntity.ok(bookService.getAllBooksByGenreId(id));
    }

    @GetMapping(params = "published_year")
    public ResponseEntity<List<BookResponseDto>> getAllBooksByPublishedYear(@RequestParam("published_year") String publishedYear) {
        log.info("Retrieve all Books by published year: {} attempt", publishedYear);
        return ResponseEntity.ok(bookService.getAllBooksByPublishedYear(publishedYear));
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        log.info("Retrieve all Books attempt");
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
