package com.sudoku.validator;

import com.sudoku.model.SudokuBoard;

public class SudokuValidator {
    private SudokuBoard board;

    public SudokuValidator(SudokuBoard board) {
        this.board = board;
    }

    public boolean isValidMove(int row, int col, int value) {
        return !isInRow(row, value) && !isInCol(col, value) && !isInBlock(row, col, value);
    }

    private boolean isInRow(int row, int value) {
        for (int col = 0; col < 9; col++) {
            if (board.getCell(row, col).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCol(int col, int value) {
        for (int row = 0; row < 9; row++) {
            if (board.getCell(row, col).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBlock(int row, int col, int value) {
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board.getCell(r, c).getValue() == value) {
                    return true;
                }
            }
        }
        return false;
    }
}
