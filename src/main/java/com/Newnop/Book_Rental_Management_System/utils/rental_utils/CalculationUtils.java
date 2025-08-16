package com.Newnop.Book_Rental_Management_System.utils.rental_utils;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class CalculationUtils {
    private CalculationUtils() {
        // Private constructor to prevent instantiation
    }

    // Method to calculate expected return date based on the number of days
    public static LocalDate calculateReturnDate(Integer expectedReturnDays) {
        if (expectedReturnDays == null || expectedReturnDays <= 0) {
            throw new IllegalArgumentException("Expected return days must be a positive integer.");
        }
        return LocalDate.now().plusDays(expectedReturnDays);
    }

    // Method to calculate total amount based on expected return date
    public static BigDecimal calculateTotalAmount(Integer expectedReturnDate) {
        // Assuming a fixed daily rental rate of 10.0 for simplicity
        BigDecimal dailyRentalRate = BigDecimal.valueOf(10.0);
        return dailyRentalRate.multiply(BigDecimal.valueOf(expectedReturnDate));
    }

    public static BigDecimal calculateRentalFee(LocalDate rentedDate, LocalDate actualReturnDate) {
        if (rentedDate == null || actualReturnDate == null) {
            throw new IllegalArgumentException("Rented date and actual return date must not be null.");
        }

        long daysRented = DAYS.between(rentedDate, actualReturnDate);

        if (daysRented < 0) {
            throw new IllegalArgumentException("Actual return date cannot be before rented date.");
        }

        // Ensure at least 1 day is charged
        long chargeableDays = (daysRented == 0) ? 1 : daysRented;

        return calculateTotalAmount((int) chargeableDays);
    }
}
