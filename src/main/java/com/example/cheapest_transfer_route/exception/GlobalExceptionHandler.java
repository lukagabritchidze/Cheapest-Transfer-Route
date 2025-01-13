package com.example.cheapest_transfer_route.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;

/**
 * A global exception handler for handling various exceptions across the application.
 * Provides a centralized mechanism for handling errors and returning appropriate responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions when the input data fails validation constraints.
     *
     * @param ex The exception thrown due to validation errors
     * @return A ResponseEntity containing a map of field names and their respective error messages
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * Handles custom TransferRouteException and returns a descriptive error message.
     *
     * @param ex The custom exception thrown in the application
     * @return A ResponseEntity with the exception message and a 400 BAD_REQUEST status
     */
    @ExceptionHandler(TransferRouteException.class)
    public ResponseEntity<String> handleTransferRouteException(TransferRouteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles generic exceptions that are not explicitly handled by other methods.
     *
     * @param ex The unexpected exception
     * @return A ResponseEntity with a generic error message and a 500 INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }

    /**
     * Handles exceptions caused by invalid or malformed JSON in the request body.
     *
     * @param ex The exception caused by unreadable HTTP messages
     * @return A ResponseEntity with an appropriate error message and a 400 BAD_REQUEST status
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid data type in the input. Ensure all fields have the correct data types.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Malformed JSON request.");
    }
}
