package com.Newnop.Book_Rental_Management_System.utils.common_utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Utility class for validation methods used across the application.
 * This class provides methods to validate common entities like IDs, Strings, etc...
 */
@Slf4j
@Component
public class ValidationUtils {
    private ValidationUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Validates a Long ID for any entity.
     *
     * @param entityName the name of the entity (for logging and exception messages)
     * @param id         the ID to validate
     * @throws IllegalArgumentException if ID is null or not positive
     */
    public static void validateId(String entityName, Long id) {
        if (id == null || id <= 0) {
            log.error("Invalid {} ID: {}", entityName, id);
            throw new IllegalArgumentException(entityName + " ID must be a valid positive integer");
        }
        log.info("Validated {} ID: {}", entityName, id);
    }

    /**
     * Validates a String field for null or empty values.
     *
     * @param fieldName the name of the field (for logging and exception messages)
     * @param value     the String value to validate
     * @throws IllegalArgumentException if the value is null or empty
     */
    public static void validateString(String fieldName, String value) {
        if (value == null || value.isBlank()) {
            log.error("Invalid {}: {}", fieldName, value);
            throw new IllegalArgumentException(fieldName + " must not be null or empty");
        }
    }

    public static void validateEmail(String entityName, String email) {
        if (email == null || email.isBlank()) {
            log.error("Invalid {} email: {}", entityName, email);
            throw new IllegalArgumentException(entityName + " email must not be null or empty");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            log.error("Invalid {} email format: {}", entityName, email);
            throw new IllegalArgumentException(entityName + " email format is invalid");
        }
        log.info("Validated {} email: {}", entityName, email);
    }
}
