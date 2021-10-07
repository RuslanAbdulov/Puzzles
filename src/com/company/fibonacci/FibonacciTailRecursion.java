package com.company.fibonacci;

public class FibonacciTailRecursion implements Fibonacci {

    @Override
    public long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N must be greater or equal to 0");
        }

        if (n <= 1) {
            return 1;
        }

        return tailRec(2, 1, 1, n);
    }

    private long tailRec(int i, long prev1, long prev2, int n) {
        long current = prev1 + prev2;
        if (i == n) {
            return current;
        }

        return tailRec(++i, current, prev1, n);
    }

}
