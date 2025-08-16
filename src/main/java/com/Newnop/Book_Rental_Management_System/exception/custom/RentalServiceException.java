package com.Newnop.Book_Rental_Management_System.exception.custom;

public class RentalServiceException extends RuntimeException {
    public RentalServiceException(String message) {
        super(message);
    }

    public RentalServiceException(String errorWhileAddingRental, Exception e) {
    }
}
