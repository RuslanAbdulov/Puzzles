package com.company.fibonacci;

import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class FibonacciTest {

//    Fibonacci fibonacci = new FibonacciNaive();
    Fibonacci fibonacci = new FibonacciTailRecursion();

    @Test
    public void testBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> fibonacci.fibonacci(-1));
    }

    @Test
    public void testCornerCases() {
        assertEquals(1, fibonacci.fibonacci(0));
        assertEquals(1, fibonacci.fibonacci(1));
    }

    @Test
    public void testNormalCourse() {
        assertEquals(55, fibonacci.fibonacci(9));
    }

    @Test
    public void testBigNumber() {
        assertEquals(1298777728820984005L, fibonacci.fibonacci(100));
    }

}