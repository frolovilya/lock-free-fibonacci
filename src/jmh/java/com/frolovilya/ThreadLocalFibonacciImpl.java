package com.frolovilya;

import java.math.BigInteger;

/**
 * Fibonacci numbers generation using ThreadLocals
 */
public class ThreadLocalFibonacciImpl {

    private ThreadLocal<BigInteger> prev = ThreadLocal.withInitial(() -> BigInteger.valueOf(-1));
    private ThreadLocal<BigInteger> current = ThreadLocal.withInitial(() -> BigInteger.valueOf(1));

    public BigInteger next() {
        BigInteger nextNumber = prev.get().add(current.get());
        prev.set(current.get());
        current.set(nextNumber);
        return nextNumber;
    }

}
