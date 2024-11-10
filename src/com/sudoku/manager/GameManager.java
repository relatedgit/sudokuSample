package com.sudoku.manager;

import com.sudoku.event.EventPublisher;
import com.sudoku.model.SudokuBoard;
import com.sudoku.validator.SudokuValidator;
import com.sudoku.solver.SudokuSolver;
import com.sudoku.rate.RateLimiter;
import com.sudoku.exception.SudokuValidationException;
import com.sudoku.exception.RateLimitException;
import com.sudoku.event.GameEventPublisher;
import com.sudoku.logging.GameLoggerSingleton;
import com.sudoku.model.User;

import java.util.ArrayList;
import java.util.List;

public class GameManager implements EventPublisher {
    private User user;
    private SudokuBoard board;
    private SudokuValidator validator;
    private SudokuSolver solver;
    private RateLimiter rateLimiter;
    private GameLoggerSingleton logger;
    private List<GameObserver> observers;
    private GameEventPublisher eventPublisher;

    public GameManager(User user, SudokuBoard board, SudokuValidator validator, SudokuSolver solver, RateLimiter rateLimiter) {
        this.user = user;
        this.board = board;
        this.validator = validator;
        this.solver = solver;
        this.rateLimiter = rateLimiter;
        this.logger = GameLoggerSingleton.getInstance();  // Get singleton instance of GameLogger
        this.observers = new ArrayList<>();
        this.eventPublisher = new GameEventPublisher();  // Create an instance of GameEventPublisher
    }

    @Override
    public void subscribe(GameObserver observer) {
        eventPublisher.subscribe(observer);  // Delegate subscription to the eventPublisher
    }

    @Override
    public void unsubscribe(GameObserver observer) {
        eventPublisher.unsubscribe(observer);  // Delegate unsubscription to the eventPublisher
    }

    @Override
    public void notifyObservers(String event) {
        eventPublisher.notifyObservers(event);  // Notify all observers
    }

    public void makeMove(int row, int col, int value) throws SudokuValidationException, RateLimitException {
        // Check the rate limit before allowing the move
        rateLimiter.validateRateLimit();

        if (validator.isValidMove(row, col, value)) {
            board.getCell(row, col).setValue(value);
            user.incrementScore(10);  // +10 points for a valid move
            user.incrementAttempts();
            logger.log("User " + user.getUsername() + " made a valid move at (" + row + "," + col + ")");
            notifyObservers("Valid move made by " + user.getUsername());
        } else {
            // If the move is invalid, throw a custom exception
            throw new SudokuValidationException("Invalid move at (" + row + "," + col + ") with value " + value);
        }
    }

    public void solvePuzzle() {
        if (solver.solve()) {
            user.incrementScore(100); // +100 points for solving the puzzle
            logger.log("Puzzle solved by " + user.getUsername());
            notifyObservers("Puzzle solved by " + user.getUsername());
        } else {
            logger.log("Puzzle could not be solved.");
            notifyObservers("Puzzle could not be solved.");
        }
    }

    public void showBoard() {
        board.printBoard();
    }

    public void getUserStatus() {
        System.out.println("User: " + user.getUsername() + ", Score: " + user.getScore() + ", Attempts: " + user.getAttempts());
    }
}
