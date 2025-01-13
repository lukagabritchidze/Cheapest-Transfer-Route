package com.example.cheapest_transfer_route.exception;

// Custom exception class that extends RuntimeException
public class TransferRouteException extends RuntimeException {

    // Constructor that accepts a custom error message
    public TransferRouteException(String message) {
        // Passing the message to the parent RuntimeException class
        super(message);
    }
}
