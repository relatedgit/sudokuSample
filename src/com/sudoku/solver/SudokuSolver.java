package com.sudoku.solver;

import com.sudoku.model.SudokuBoard;
import com.sudoku.validator.SudokuValidator;
import com.sudoku.exception.SudokuValidationException;

public class SudokuSolver {
    private SudokuBoard board;
    private SudokuValidator validator;

    public SudokuSolver(SudokuBoard board, SudokuValidator validator) {
        this.board = board;
        this.validator = validator;
    }

    // Method to solve the Sudoku puzzle using backtracking
    public boolean solve() {
        return solveSudoku(board);
    }

    private boolean solveSudoku(SudokuBoard board) {
        // Find the next empty cell
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getCell(row, col).getValue() == 0) { // Empty cell
                    // Try every number from 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        if (validator.isValidMove(row, col, num)) {
                            board.getCell(row, col).setValue(num);
                            // Recursively try to solve the rest of the puzzle
                            if (solveSudoku(board)) {
                                return true;
                            }
                            // If placing num doesn't lead to a solution, reset the cell
                            board.getCell(row, col).setValue(0);
                        }
                    }
                    return false; // No valid number found for this cell
                }
            }
        }
        return true; // All cells are filled correctly
    }
}
