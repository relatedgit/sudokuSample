package com.sudoku.model;

public class SudokuBoard {
    private SudokuCell[][] grid;

    public SudokuBoard(int[][] initialGrid) {
        grid = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = new SudokuCell(initialGrid[i][j]);
            }
        }
    }

    public SudokuCell getCell(int row, int col) {
        return grid[row][col];
    }

    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j].getValue() == 0 ? "." : grid[i][j].getValue());
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public boolean isFilled() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j].isEmpty()) return false;
            }
        }
        return true;
    }
}
