package intterra_interview;

import java.math.BigInteger;
import java.util.stream.Stream;


public class Fibonacci {

    static Stream<BigInteger> fibonacci() {
        return Stream.iterate(new BigInteger[] {BigInteger.ZERO, BigInteger.ONE}, Fibonacci::next)
                .map(tuple -> tuple[1]);
    }

    private static BigInteger[] next(BigInteger[] prev) {
        return new BigInteger[] {prev[1], prev[0].add(prev[1])};
    }

}
