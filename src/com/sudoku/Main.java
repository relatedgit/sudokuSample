package com.sudoku;

import com.sudoku.exception.RateLimitException;
import com.sudoku.exception.SudokuValidationException;
import com.sudoku.manager.EventObserver;
import com.sudoku.manager.GameManager;
import com.sudoku.model.SudokuBoard;
import com.sudoku.model.User;
import com.sudoku.rate.RateLimiter;
import com.sudoku.rate.RateLimiterImpl;
import com.sudoku.solver.SudokuSolver;
import com.sudoku.validator.SudokuValidator;

public class Main {
    public static void main(String[] args) {
        int[][] initialBoard = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuBoard board = new SudokuBoard(initialBoard);
        SudokuValidator validator = new SudokuValidator(board);
        SudokuSolver solver = new SudokuSolver(board, validator);
        User user = new User("Player1");
        RateLimiter rateLimiter = new RateLimiterImpl(5, 60000); // 5 moves per 60 seconds

        GameManager gameManager = new GameManager(user, board, validator, solver, rateLimiter);

        // Create an observer and subscribe to the game manager
        EventObserver eventObserver = new EventObserver();
        gameManager.subscribe(eventObserver);

        try {
            gameManager.makeMove(0, 2, 4);  // Example move
            gameManager.showBoard();
            gameManager.getUserStatus();

            gameManager.solvePuzzle(); // Solve the puzzle
            gameManager.showBoard();
        } catch (SudokuValidationException e) {
            System.out.println("Error: " + e.getMessage()); // Handle invalid move
        } catch (RateLimitException e) {
            System.out.println("Rate limit exceeded: " + e.getMessage()); // Handle rate limit exception
        }
    }
}
