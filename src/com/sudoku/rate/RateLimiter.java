package com.sudoku.rate;

import com.sudoku.exception.RateLimitException;

public interface RateLimiter {
    void validateRateLimit() throws RateLimitException;
}
