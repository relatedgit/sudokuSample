package com.sudoku.logging;

public class GameLoggerSingleton implements GameLogger {
    private static GameLoggerSingleton instance;

    private GameLoggerSingleton() {}

    public static GameLoggerSingleton getInstance() {
        if (instance == null) {
            instance = new GameLoggerSingleton();
        }
        return instance;
    }

    @Override
    public void log(String message) {
        System.out.println("[LOG]: " + message);
    }
}
