package com.ironhack.duckytime.exceptions;

public class EntryException extends RuntimeException {
    public EntryException(String message) {
        super("Entry denied: " + message);
    }
}
