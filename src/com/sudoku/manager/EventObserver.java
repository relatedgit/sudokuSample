package com.sudoku.manager;

public class EventObserver implements GameObserver {
    @Override
    public void update(String event) {
        System.out.println("Event received: " + event);  // Just print the event message
    }
}
