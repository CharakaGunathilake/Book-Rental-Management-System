package com.Newnop.Book_Rental_Management_System.exception.custom;

public class BookServiceException extends RuntimeException {
    public BookServiceException(String message) {
        super(message);
    }

    public BookServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
