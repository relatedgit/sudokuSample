package com.sudoku.rate;

import com.sudoku.exception.RateLimitException;

public class RateLimiterImpl implements RateLimiter {
    private final int maxAttempts;
    private int attemptCount;
    private long lastResetTime;

    public RateLimiterImpl(int attempts, int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.attemptCount = 0;
        this.lastResetTime = System.currentTimeMillis();
    }

    @Override
    public void validateRateLimit() throws RateLimitException {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastResetTime > 60000) { // Reset every minute
            attemptCount = 0;
            lastResetTime = currentTime;
        }
        if (attemptCount >= maxAttempts) {
            throw new RateLimitException("Rate limit exceeded. Please try again later.");
        }
        attemptCount++;
    }
}
