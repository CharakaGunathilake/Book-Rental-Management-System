package com.Newnop.Book_Rental_Management_System.service.impl;

import com.Newnop.Book_Rental_Management_System.dto.request.RentalRequestDto;
import com.Newnop.Book_Rental_Management_System.dto.response.RentalResponseDto;
import com.Newnop.Book_Rental_Management_System.repository.RentalRepository;
import com.Newnop.Book_Rental_Management_System.service.RentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final ObjectMapper objectMapper;


    @Override
    public RentalResponseDto addRental(RentalRequestDto rentalRequestDto) {
        return null;
    }

    @Override
    public RentalResponseDto updateRental(RentalRequestDto rentalRequestDto) {
        return null;
    }

    @Override
    public void deleteRental(Long rentalId) {

    }

    @Override
    public void returnBook(Long rentalId) {

    }

    @Override
    public void cancelRental(Long rentalId) {

    }

    @Override
    public BigDecimal calculateRentalFee(Long rentalId) {
        return null;
    }

    @Override
    public RentalResponseDto getRentalById(Long rentalId) {
        return null;
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByBookId(Long BookId) {
        return List.of();
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByStatus(String status) {
        return List.of();
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByRentedDate(LocalDate rentedDate) {
        return List.of();
    }

    @Override
    public List<RentalResponseDto> getAllRentalsByReturnDate(LocalDate returnDate) {
        return List.of();
    }

    @Override
    public List<RentalResponseDto> getAllRentals() {
        return List.of();
    }
}
