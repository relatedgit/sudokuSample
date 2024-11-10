package com.sudoku.exception;

public class RateLimitException extends Exception {
    public RateLimitException(String message) {
        super(message);
    }
}

