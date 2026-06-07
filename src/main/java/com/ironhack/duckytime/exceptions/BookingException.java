package com.ironhack.duckytime.exceptions;

public class BookingException extends RuntimeException {
    public BookingException(String message) {
        super("Could not book: " + message);
    }
}
