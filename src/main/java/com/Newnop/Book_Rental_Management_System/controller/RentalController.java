package com.Newnop.Book_Rental_Management_System.controller;

import com.Newnop.Book_Rental_Management_System.dto.request.RentalRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.RentalResponseDto;
import com.Newnop.Book_Rental_Management_System.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/rental")
@RequiredArgsConstructor
public class RentalController {
    // This class handles rental-related endpoints.
    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<?> addRental(@Valid @RequestBody RentalRequestDto rentalRequestDto) {
        log.info("Create new Rental attempt");
        return ResponseEntity.status(HttpStatus.CREATED).body(rentalService.addRental(rentalRequestDto));
    }

    @PutMapping
    public ResponseEntity<?> updateRental(@Valid @RequestBody RentalRequestDto rentalRequestDto) {
        log.info("Update existing Rental attempt");
        return ResponseEntity.ok(rentalService.updateRental(rentalRequestDto));
    }

    @PatchMapping("/cancel")
    public ResponseEntity<Void> cancelRental(@RequestParam Long id) {
        log.info("Cancel rental attempt");
        rentalService.cancelRental(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/return")
    public ResponseEntity<Void> returnRental(@RequestParam Long id) {
        log.info("Return rental attempt");
        rentalService.returnBook(id);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRental(@RequestParam Long id) {
        log.info("Delete existing Rental attempt");
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/calculate")
    public ResponseEntity<BigDecimal> calculateRentalPrice(@RequestParam Long id) {
        log.info("Calculate rental price attempt");
        return ResponseEntity.ok(rentalService.calculateRentalFee(id));
    }

    @GetMapping
    public ResponseEntity<RentalResponseDto> getRentalById(@RequestParam Long id) {
        log.info("Retrieve rental attempt");
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @GetMapping("/by_user")
    public ResponseEntity<List<RentalResponseDto>> getAllRentalsByUserId(@RequestParam Long id) {
        log.info("Retrieve all rentals for user attempt");
        return ResponseEntity.ok(rentalService.getAllRentalsByUserId(id));
    }

    @GetMapping("/by_book")
    public ResponseEntity<List<RentalResponseDto>> getAllRentalsByBookId(@RequestParam Long id) {
        log.info("Retrieve all rentals for book attempt");
        return ResponseEntity.ok(rentalService.getAllRentalsByBookId(id));
    }

    @GetMapping("/by_status")
    public ResponseEntity<List<RentalResponseDto>> getAllRentalsByStatus(@RequestParam String status) {
        log.info("Retrieve all rentals by status attempt");
        return ResponseEntity.ok(rentalService.getAllRentalsByStatus(status));
    }

    @GetMapping("/by_rented_date")
    public ResponseEntity<List<RentalResponseDto>> getAllRentalsByRentedDate(@RequestParam LocalDate date) {
        log.info("Retrieve all rentals by rented date attempt");
        return ResponseEntity.ok(rentalService.getAllRentalsByRentedDate(date));
    }

    @GetMapping("/by_return_date")
    public ResponseEntity<List<RentalResponseDto>> getAllRentalsByReturnDate(@RequestParam LocalDate date) {
        log.info("Retrieve all rentals by return date attempt");
        return ResponseEntity.ok(rentalService.getAllRentalsByReturnDate(date));
    }

    @GetMapping("/all")
    public ResponseEntity<List<RentalResponseDto>> getAllRental() {
        log.info("Retrieve all Rentals attempt");
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
}
