package intterra_interview;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    void fibonacci6() {
        testFibonacci(6, BigInteger.valueOf(13));
    }

    @Test
    void fibonacci1() {
        testFibonacci(0, BigInteger.valueOf(1));
    }

    @Test
    void fibonacci2() {
        testFibonacci(1, BigInteger.valueOf(1));
    }

    @Test
    void fibonacci3() {
        testFibonacci(2, BigInteger.valueOf(2));
    }

    @Test
    void fibonacci100() {
        testFibonacci(100, new BigInteger("573147844013817084101"));
    }

    @Test
    void sumFirst5() {
        assertEquals(BigInteger.valueOf(12), Fibonacci.fibonacci()
                .limit(5)
                .reduce(BigInteger.ZERO, BigInteger::add));
    }

    private Optional<BigInteger> find(Stream<BigInteger> stream, int index) {
        return stream.skip(index).findFirst();
    }

    private Optional<BigInteger> findFibonacci(int index) {
        return find(Fibonacci.fibonacci(), index);
    }

    private void testFibonacci(int index, BigInteger expected) {
        Optional<BigInteger> number = findFibonacci(index);
        assertTrue(number.isPresent());
        assertEquals(expected, number.get());
    }

}
