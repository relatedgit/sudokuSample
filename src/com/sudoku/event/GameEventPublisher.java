package com.sudoku.event;

import com.sudoku.manager.GameObserver;
import java.util.ArrayList;
import java.util.List;

public class GameEventPublisher implements com.sudoku.event.EventPublisher {
    private List<GameObserver> observers;  // List to hold the observers

    public GameEventPublisher() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void subscribe(GameObserver observer) {
        observers.add(observer);  // Add the observer to the list
    }

    @Override
    public void unsubscribe(GameObserver observer) {
        observers.remove(observer);  // Remove the observer from the list
    }

    @Override
    public void notifyObservers(String event) {
        for (GameObserver observer : observers) {
            observer.update(event);  // Notify each observer about the event
        }
    }
}
