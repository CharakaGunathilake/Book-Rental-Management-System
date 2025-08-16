package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.BookRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.BookResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Author;
import com.Newnop.Book_Rental_Management_System.entity.Book;
import com.Newnop.Book_Rental_Management_System.entity.Genre;
import com.Newnop.Book_Rental_Management_System.enums.AvailabilityStatus;
import com.Newnop.Book_Rental_Management_System.enums.BookQuality;
import com.Newnop.Book_Rental_Management_System.exception.custom.BookServiceException;
import com.Newnop.Book_Rental_Management_System.repository.AuthorRepository;
import com.Newnop.Book_Rental_Management_System.repository.BookRepository;
import com.Newnop.Book_Rental_Management_System.repository.GenreRepository;
import com.Newnop.Book_Rental_Management_System.service.BookService;
import com.Newnop.Book_Rental_Management_System.utils.book_utils.BookMapper;
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
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        try {
            // Fetch related entities
            Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                    .orElseThrow(() -> new EntityNotFoundException("Author not found with id: " + bookRequestDto.getAuthorId()));
            Genre genre = genreRepository.findById(bookRequestDto.getGenreId())
                    .orElseThrow(() -> new EntityNotFoundException("Genre not found with id: " + bookRequestDto.getGenreId()));

            // Map DTO to entity manually or with ModelMapper
            Book book = BookMapper.toEntity(bookRequestDto, author, genre);

            // Save entity
            Book savedBook = bookRepository.save(book);

            log.info("Book created successfully with ID: {}", savedBook.getId());

            // Map to response DTO
            return BookMapper.toDto(savedBook);
        } catch (DataAccessException e) {
            // Handles DB-specific errors (e.g., constraint violations, duplicates)
            log.error("Database error while creating book: {}", e.getMessage(), e);
            throw new BookServiceException("Database error while creating book", e);
        } catch (IllegalArgumentException e) {
            // Handles invalid arguments in mapping/validation
            log.error("Invalid input when creating book: {}", e.getMessage(), e);
            throw new BookServiceException("Invalid input for book creation", e);
        } catch (EntityNotFoundException e) {
            log.error("Error while adding book", e);
            throw e;
        } catch (Exception e) {
            log.error("Error while adding book", e);
            throw new BookServiceException("Failed to create book");
        }
    }


    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        // Validate book ID
        ValidationUtils.validateId("Book", id);

        // Fetch existing book
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        // Update fields
        BookMapper.updateEntity(bookRequestDto, book);

        // Update relationships manually after validating if not and null and if they have changed
        if (bookRequestDto.getAuthorId() != null && !bookRequestDto.getAuthorId().equals(book.getAuthor().getId())) {
            Author author = authorRepository.findById(bookRequestDto.getAuthorId())
                    .orElseThrow(() -> new EntityNotFoundException("Author not found with id" + bookRequestDto.getAuthorId()));
            book.setAuthor(author);
        }

        if (bookRequestDto.getGenreId() != null && !bookRequestDto.getGenreId().equals(book.getGenre().getId())) {
            Genre genre = genreRepository.findById(bookRequestDto.getGenreId())
                    .orElseThrow(() -> new EntityNotFoundException("Genre not found with id" + bookRequestDto.getGenreId()));
            book.setGenre(genre);
        }
        Book updatedBook;
        try {
            // Save updated book
            updatedBook = bookRepository.save(book);
        } catch (DataAccessException e) {
            log.error("Database error while updating book with ID {}: {}", id, e.getMessage(), e);
            throw new BookServiceException("Database error while updating book", e);
        }

        log.info("Book updated successfully with ID: {}", updatedBook.getId());

        // Map to response DTO
        return BookMapper.toDto(updatedBook);
    }

    @Override
    public BookResponseDto updateBookAvailabilityStatus(Long bookId, String status) {
        // Validate book ID
        ValidationUtils.validateId("Book", bookId);

        // Validate availability status
        if (status == null || status.isBlank()) {
            log.error("Invalid availability status: {}", status);
            throw new IllegalArgumentException("Availability status must not be null or empty");
        }

        // Convert status string to uppercase to match enum values
        AvailabilityStatus availabilityStatus;
        try {
            // Trim and convert to uppercase to match enum values
            availabilityStatus = AvailabilityStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid Availability status: {}", status, e);
            throw new IllegalArgumentException("Invalid availability status: " + status);
        }

        // Fetch book by ID
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));

        // Update availability status
        book.setAvailabilityStatus(availabilityStatus);
        try {
            // Save updated book
            Book updatedBook = bookRepository.save(book);

            log.info("Book availability status updated successfully with ID: {}", updatedBook.getId());

            // Map to response DTO
            return BookMapper.toDto(updatedBook);
        } catch (DataAccessException e) {
            log.error("Database error while updating book availability status with ID {}: {}", bookId, e.getMessage(), e);
            throw new BookServiceException("Database error while updating book", e);
        }
    }

    @Override
    public BookResponseDto updateBookQuality(Long bookId, String quality) {
        // Validate book ID
        ValidationUtils.validateId("Book", bookId);

        // Validate book quality
        if (quality == null || quality.isBlank()) {
            log.error("Invalid book quality: {}", quality);
            throw new IllegalArgumentException("Book quality must not be null or empty");
        }

        // Convert quality string to enum
        BookQuality bookQuality;
        try {
            // Trim and convert to uppercase to match enum values
            bookQuality = BookQuality.valueOf(quality.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid book quality: {}", quality, e);
            throw new IllegalArgumentException("Invalid book quality: " + quality);
        }

        // Fetch book by ID
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));

        // Update book quality
        book.setBookQuality(bookQuality);

        // Save updated book
        Book updatedBook = bookRepository.save(book);

        log.info("Book quality updated successfully with ID: {}", updatedBook.getId());

        // Map to response DTO
        return BookMapper.toDto(updatedBook);
    }

    @Override
    public void deleteBook(Long bookId) {
        try {
            // Validate book ID
            ValidationUtils.validateId("Book", bookId);
            // Check if book exists
            if (!bookRepository.existsById(bookId)) {
                log.error("Book with ID {} not found for deletion", bookId);
                throw new EntityNotFoundException("Book not found with ID: " + bookId);
            }
            // Delete book
            bookRepository.deleteById(bookId);
            log.info("Book deleted successfully with ID: {}", bookId);
        } catch (EntityNotFoundException e) {
            log.error("Error while deleting book", e);
            throw e; // Re-throw the exception to be handled by the controller
        } catch (Exception e) {
            log.error("Error while deleting book", e);
            throw new RuntimeException("Failed to delete book");
        }
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        // Validate book ID
        ValidationUtils.validateId("Book", bookId);

        // Fetch book by ID
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + bookId));

        log.info("Book retrieved successfully with ID: {}", book.getId());
        // Map to response DTO
        return BookMapper.toDto(book);
    }

    @Override
    public BookResponseDto getBookByName(String bookName) {
        // Validate book name
        if (bookName == null || bookName.isBlank()) {
            log.warn("Invalid book name: {}", bookName);
            throw new IllegalArgumentException("Book name must not be null or empty");
        }

        // Trim the book name to avoid leading/trailing spaces
        String finalBookName = bookName.trim();

        // Fetch book by name
        Book book = bookRepository.findByTitleIgnoreCase(finalBookName)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with name: " + finalBookName));

        log.info("Book retrieved successfully with name: {}", book.getTitle());
        // Map to response DTO
        return BookMapper.toDto(book);
    }

    @Override
    public List<BookResponseDto> getAllBooksByAuthorId(Long authorId) {
        // Validate author ID
        ValidationUtils.validateId("Author", authorId);

        // Check if author exists
        if (!authorRepository.existsById(authorId)) {
            throw new EntityNotFoundException("Author not found with id: " + authorId);
        }
        // Fetch books by author ID
        List<Book> books = bookRepository.findAllByAuthor_Id(authorId);

        // Check if books are empty
        if (books.isEmpty()) {
            log.info("No books found with author id: {}", authorId);
            return List.of(); // Return an empty list if no books found
        }

        log.info("Found {} books with author id: {}", books.size(), authorId);
        // Map to response DTOs
        return books.stream()
                .map(BookMapper::toDto)
                .toList();

    }

    @Override
    public List<BookResponseDto> getAllBooksByGenreId(Long genreId) {
        // Validate genre ID
        ValidationUtils.validateId("Genre", genreId);

        // Check if genre exists
        if (!genreRepository.existsById(genreId)) {
            throw new EntityNotFoundException("Genre not found with id: " + genreId);
        }

        // Fetch books by genre ID
        List<Book> books = bookRepository.findAllByGenre_Id(genreId);

        ////Check if books are empty
        if (books.isEmpty()) {
            log.info("No books found with genre id: {}", genreId);
            return List.of(); // Return an empty list if no books found
        }

        log.info("Found {} books with genre id: {}", books.size(), genreId);
        // Map to response DTOs
        return books.stream()
                .map(BookMapper::toDto)
                .toList();

    }

    @Override
    public List<BookResponseDto> getAllBooksByAvailabilityStatus(String status) {
        // Validate availability status
        ValidationUtils.validateString("Availability status", status);

        // Convert status string to uppercase to match enum values
        AvailabilityStatus availabilityStatus;
        try {
            // Trim and convert to uppercase to match enum values
            availabilityStatus = AvailabilityStatus.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid Availability status: {}", status, e);
            throw new IllegalArgumentException("Invalid availability status: " + status);
        }

        // Fetch books by availability status
        List<Book> books = bookRepository.findAllByAvailabilityStatus(availabilityStatus);

        //Check if books are empty
        if (books.isEmpty()) {
            log.info("No books found with availability status: {}", status);
            return List.of(); // Return an empty list if no books found
        }

        log.info("Found {} books with availability status: {}", books.size(), status);
        // Map to response DTOs
        return books.stream()
                .map(BookMapper::toDto)
                .toList();

    }

    @Override
    public List<BookResponseDto> getAllBooksByQuality(String quality) {
        // Validate book quality
        ValidationUtils.validateString("Book quality", quality);

        // Convert quality string to enum
        BookQuality qualityEnum;
        try {
            // Trim and convert to uppercase to match enum values
            qualityEnum = BookQuality.valueOf(quality.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid book quality: {}", quality, e);
            throw new IllegalArgumentException("Invalid book quality: " + quality);
        }

        // Fetch books by quality
        List<Book> books = bookRepository.findAllByBookQuality(qualityEnum);

        // Check if books are empty
        if (books.isEmpty()) {
            log.info("No books found with quality: {}", quality);
            return List.of();
        }

        log.info("Found {} books with quality: {}", books.size(), quality);
        return books.stream()
                .map(BookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> getAllBooksByPublishedYear(Integer year) {
        // Check if year is valid
        if (year == null || year < 0) {
            log.warn("Invalid published year: {}", year);
            throw new IllegalArgumentException("Published year must be a valid positive integer");
        }

        // Fetch books by published year
        List<Book> books = bookRepository.findAllByPublishedYear(year);

        // Check if books are empty
        if (books.isEmpty()) {
            log.info("No books found with published year: {}", year);
            return List.of(); // Return an empty list if no books found
        }

        log.info("Found {} books with published year: {}", books.size(), year);
        // Map to response DTOs
        return books.stream()
                .map(BookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        // Fetch all books
        List<Book> books = bookRepository.findAll();

        // Check if books are empty
        if (books.isEmpty()) {
            log.info("No books found");
            return List.of(); // Return an empty list if no books found
        }
        log.info("Found {} books", books.size());
        // Map to response DTOs
        return books.stream()
                .map(BookMapper::toDto)
                .toList();
    }
}
