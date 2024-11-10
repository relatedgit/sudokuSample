package com.sudoku.model;

public class SudokuCell {
    private int value;
    private boolean isLocked;

    public SudokuCell(int value) {
        this.value = value;
        this.isLocked = (value != 0);  // Locked if the value is set (not empty)
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (isLocked) {
            throw new UnsupportedOperationException("Cannot modify locked cells.");
        }
        this.value = value;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isEmpty() {
        return value == 0;
    }
}
