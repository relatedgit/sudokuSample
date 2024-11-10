package com.sudoku.model;

public class User {
    private String username;
    private int score;
    private int attempts;

    public User(String username) {
        this.username = username;
        this.score = 0;  // Initialize score to 0
        this.attempts = 0;  // Initialize attempts to 0
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public int getAttempts() {
        return attempts;
    }

    public void incrementScore(int points) {
        this.score += points;
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    public void resetAttempts() {
        this.attempts = 0;
    }
}
