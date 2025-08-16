package com.Newnop.Book_Rental_Management_System.exception.custom;

public class AuthorServiceException extends RuntimeException {
    public AuthorServiceException(String message) {
        super(message);
    }

    public AuthorServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
