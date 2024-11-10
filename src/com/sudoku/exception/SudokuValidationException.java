package com.sudoku.exception;

public class SudokuValidationException extends Exception {
    // Constructor that accepts a message
    public SudokuValidationException(String message) {
        super(message);  // Pass the message to the base class (Exception)
    }
}
