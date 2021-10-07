package com.company.fibonacci;

public class FibonacciNaive implements Fibonacci {

    @Override
    public long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N must be greater or equal to 0");
        }

        if (n <= 1) {
            return 1;
        }

        return fibonacci(n-2) + fibonacci(n-1);
    }
}
