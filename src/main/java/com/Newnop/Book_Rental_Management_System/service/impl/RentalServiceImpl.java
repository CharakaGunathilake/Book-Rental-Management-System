package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.RentalRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.RentalResponseDto;
import com.Newnop.Book_Rental_Management_System.entity.Book;
import com.Newnop.Book_Rental_Management_System.entity.Rental;
import com.Newnop.Book_Rental_Management_System.entity.User;
import com.Newnop.Book_Rental_Management_System.enums.RentalStatus;
import com.Newnop.Book_Rental_Management_System.exception.custom.BookServiceException;
import com.Newnop.Book_Rental_Management_System.exception.custom.RentalServiceException;
import com.Newnop.Book_Rental_Management_System.repository.BookRepository;
import com.Newnop.Book_Rental_Management_System.repository.RentalRepository;
import com.Newnop.Book_Rental_Management_System.repository.UserRepository;
import com.Newnop.Book_Rental_Management_System.service.RentalService;
import com.Newnop.Book_Rental_Management_System.utils.common_utils.ValidationUtils;
import com.Newnop.Book_Rental_Management_System.utils.rental_utils.CalculationUtils;
import com.Newnop.Book_Rental_Management_System.utils.rental_utils.RentalMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public RentalResponseDto addRental(RentalRequestDto rentalRequestDto) {
        // Fetch and validate the book and user
        Book book = bookRepository.findById(rentalRequestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + rentalRequestDto.getBookId()));
        User user = userRepository.findById(rentalRequestDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + rentalRequestDto.getUserId()));

        // Convert DTO to entity
        Rental rental = RentalMapper.toEntity(rentalRequestDto, book, user);

        try {
            // Save entity
            Rental savedRental = rentalRepository.save(rental);
            log.info("Rental with ID {} created successfully", savedRental.getId());
            // Convert saved entity back to DTO
            return RentalMapper.toDto(savedRental);

        } catch (DataAccessException e) {
            // Handles DB-specific errors (e.g., constraint violations, duplicates)
            log.error("Database error while creating rental: {}", e.getMessage(), e);
            throw new BookServiceException("Database error while creating rental", e);
        }
    }

    @Override
    public RentalResponseDto updateRental(Long id, RentalRequestDto rentalRequestDto) {
        // Validate rental ID
        ValidationUtils.validateId("Rental", id);

        // Fetch existing rental
        Rental existingRental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with ID: " + id));

        // Update fields as necessary;
        final Rental updatedRental = handleUpdate(existingRental, rentalRequestDto);

        try {
            // Save updated rental
            final Rental savedEntity = rentalRepository.save(updatedRental);

            log.info("Rental with ID {} updated successfully", savedEntity.getId());
            return RentalMapper.toDto(savedEntity);

        } catch (DataAccessException e) {
            log.error("Database error while updating rental: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while updating rental", e);
        }
    }

    private Rental handleUpdate(Rental existingRental, RentalRequestDto rentalRequestDto) {
        if (!existingRental.getBook().getId().equals(rentalRequestDto.getBookId())) {
            existingRental.setBook(
                    bookRepository.findById(rentalRequestDto.getBookId())
                            .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + rentalRequestDto.getBookId()))
            );
        }
        if (!existingRental.getUser().getId().equals(rentalRequestDto.getUserId())) {
            existingRental.setUser(
                    userRepository.findById(rentalRequestDto.getUserId())
                            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + rentalRequestDto.getUserId()))
            );
        }
        LocalDate calculatedReturnDate = CalculationUtils.calculateReturnDate(rentalRequestDto.getExpectedReturnDate());
        if (!existingRental.getExpectedReturnDate().equals(calculatedReturnDate)) {
            existingRental.setExpectedReturnDate(calculatedReturnDate);
            existingRental.setTotalAmount(
                    CalculationUtils.calculateTotalAmount(rentalRequestDto.getExpectedReturnDate())
            );
        }
        return existingRental;
    }

    @Override
    public void deleteRental(Long rentalId) {
        // Validate rental ID
        ValidationUtils.validateId("Rental", rentalId);
        // Check if rental exists
        if (!rentalRepository.existsById(rentalId)) {
            throw new EntityNotFoundException("Rental not found with ID: " + rentalId);
        }
        try {
            // Delete the rental
            rentalRepository.deleteById(rentalId);
            log.info("Rental with ID {} deleted successfully", rentalId);
        } catch (DataAccessException e) {
            log.error("Database error while deleting rental: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while deleting rental", e);
        }
    }

    @Override
    public void returnBook(Long rentalId) {
        // Validate rental ID
        ValidationUtils.validateId("Rental", rentalId);
        // Fetch existing rental
        Rental existingRental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with ID: " + rentalId));
        // Update the actual return date and status
        existingRental.setActualReturnDate(LocalDate.now());
        existingRental.setRentalStatus(RentalStatus.RETURNED);

        try {
            // Save updated rental
            rentalRepository.save(existingRental);
            log.info("Rental with ID {} returned successfully", rentalId);
        } catch (DataAccessException e) {
            log.error("Database error while returning book: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while returning book", e);
        }
    }

    @Override
    public void cancelRental(Long rentalId) {
        // Validate rental ID
        ValidationUtils.validateId("Rental", rentalId);
        // Fetch existing rental
        Rental existingRental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with ID: " + rentalId));
        // Update the status to CANCELED
        existingRental.setRentalStatus(RentalStatus.CANCELED);
        // Save updated rental
        try {
            rentalRepository.save(existingRental);
            log.info("Rental with ID {} canceled successfully", rentalId);
        } catch (DataAccessException e) {
            log.error("Database error while canceling rental: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while canceling rental", e);
        }

    }

    @Override
    public BigDecimal calculateRentalFee(Long rentalId) {
        // Validate rental ID
        ValidationUtils.validateId("Rental", rentalId);
        Rental existingRental;
        // Fetch existing rental
        existingRental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with ID: " + rentalId));
        // Calculate the rental fee based on the actual return date and expected return date
        BigDecimal rentalFee = CalculationUtils.calculateRentalFee(existingRental.getExpectedReturnDate(), existingRental.getActualReturnDate());
        log.info("Calculated rental fee for rental ID {}: {}", rentalId, rentalFee);
        return rentalFee;

    }

    @Override
    public RentalResponseDto getRentalById(Long rentalId) {
        // Validate rental ID
        ValidationUtils.validateId("Rental", rentalId);
        Rental existingRental;
        try {
            // Fetch existing rental
            existingRental = rentalRepository.findById(rentalId)
                    .orElseThrow(() -> new EntityNotFoundException("Rental not found with ID: " + rentalId));
        } catch (DataAccessException e) {
            log.error("Database error while retrieving rental: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while retrieving rental", e);
        }

        // Convert entity to DTO
        RentalResponseDto rentalResponse = RentalMapper.toDto(existingRental);
        log.info("Rental retrieved successfully with ID: {}", rentalId);
        return rentalResponse;
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByBookId(Long BookId) {
        // Validate book ID
        ValidationUtils.validateId("Book", BookId);
        List<Rental> rentals;
        try {
            // Fetch all rentals for the given book ID
            rentals = rentalRepository.findAllByBookId(BookId);
        } catch (DataAccessException e) {
            log.error("Database error while retrieving rentals by book ID: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while retrieving rentals by book ID", e);
        }
        // Check if rentals list is empty
        if (rentals.isEmpty()) {
            log.info("No rentals found for book ID {}", BookId);
            return List.of(); // Return empty list if no rentals found
        }

        log.info("Retrieved {} rentals for book ID {}", rentals.size(), BookId);
        // Convert entities to DTOs
        return rentals.stream()
                .map(RentalMapper::toDto)
                .toList();
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByUserId(Long userId) {
        // Validate user ID
        ValidationUtils.validateId("User", userId);
        List<Rental> rentals;
        try {
            // Fetch all rentals for the given user ID
            rentals = rentalRepository.findAllByUserId(userId);
        } catch (DataAccessException e) {
            log.error("Database error while retrieving rentals by user ID: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while retrieving rentals by user ID", e);
        }
        // Check if rentals list is empty
        if (rentals.isEmpty()) {
            log.info("No rentals found for user ID {}", userId);
            return List.of(); // Return empty list if no rentals found
        }

        log.info("Retrieved {} rentals for user ID {}", rentals.size(), userId);
        // Convert entities to DTOs
        return rentals.stream()
                .map(RentalMapper::toDto)
                .toList();
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByStatus(String status) {
        // Validate rental status
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Rental status must not be null or empty");
        }
        List<Rental> rentals;
        try {
            // Fetch all rentals by status
            rentals = rentalRepository.findAllByRentalStatus(RentalStatus.valueOf(status.toUpperCase()));
        } catch (DataAccessException e) {
            log.error("Database error while retrieving rentals by status: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while retrieving rentals by status", e);
        }
        // Check if rentals list is empty
        if (rentals.isEmpty()) {
            log.info("No rentals found for status {}", status);
            return List.of(); // Return empty list if no rentals found
        }

        log.info("Retrieved {} rentals for status {}", rentals.size(), status);
        // Convert entities to DTOs
        return rentals.stream()
                .map(RentalMapper::toDto)
                .toList();
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByRentedDate(LocalDate rentedDate) {
        // Validate rented date
        if (rentedDate == null) {
            log.error("Rented date must not be null");
            throw new IllegalArgumentException("Rented date must not be null");
        }
        List<Rental> rentals;
        try {
            // Fetch all rentals by rented date
            rentals = rentalRepository.findAllByRentedDate(rentedDate);
        } catch (DataAccessException e) {
            log.error("Database error while retrieving rentals by rented date: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while retrieving rentals by rented date", e);
        }
        // Check if rentals list is empty
        if (rentals.isEmpty()) {
            log.info("No rentals found for rented date {}", rentedDate);
            return List.of(); // Return empty list if no rentals found
        }

        log.info("Retrieved {} rentals for rented date {}", rentals.size(), rentedDate);
        // Convert entities to DTOs
        return rentals.stream()
                .map(RentalMapper::toDto)
                .toList();
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByReturnDate(LocalDate returnDate) {
        // Validate input
        if (returnDate == null) {
            log.error("Return date must not be null");
            throw new IllegalArgumentException("Return date must not be null");
        }

        List<Rental> rentals;
        try {
            rentals = rentalRepository.findAllByExpectedReturnDate(returnDate);
        } catch (DataAccessException e) {
            log.error("Database error while retrieving rentals by return date: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while retrieving rentals by return date", e);
        }

        if (rentals.isEmpty()) {
            log.info("No rentals found for return date {}", returnDate);
            return List.of();
        }

        log.info("Retrieved {} rentals for return date {}", rentals.size(), returnDate);
        return rentals.stream()
                .map(RentalMapper::toDto)
                .toList();
    }


    @Override
    public List<RentalResponseDto> getAllRentals() {
        List<Rental> rentals;
        try {
            // Fetch all rentals
            rentals = rentalRepository.findAll();
        } catch (DataAccessException e) {
            log.error("Database error while retrieving all rentals: {}", e.getMessage(), e);
            throw new RentalServiceException("Database error while retrieving all rentals", e);
        }
        // Check if rentals list is empty
        if (rentals.isEmpty()) {
            log.info("No rentals found");
            return List.of(); // Return empty list if no rentals found
        }

        log.info("Retrieved {} rentals successfully", rentals.size());
        // Convert entities to DTOs
        return rentals.stream()
                .map(RentalMapper::toDto)
                .toList();
    }
}
