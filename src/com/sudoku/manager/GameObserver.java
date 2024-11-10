package com.sudoku.manager;

public interface GameObserver {
    void update(String event);  // Method to update the observer with the event message
}
