package com.sudoku.event;

import com.sudoku.manager.GameObserver;

public interface EventPublisher {
    void subscribe(GameObserver observer);       // To subscribe an observer
    void unsubscribe(GameObserver observer);     // To unsubscribe an observer
    void notifyObservers(String event);          // To notify all observers of a game event
}
