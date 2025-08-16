package com.Newnop.Book_Rental_Management_System.exception.custom;

public class GenreServiceException extends RuntimeException {
    public GenreServiceException(String message) {
        super(message);
    }

    public GenreServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
