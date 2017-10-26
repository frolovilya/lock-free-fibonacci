package com.frolovilya;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Fibonacci numbers generation using lock
 */
public class BlockingFibonacciImpl {

    private final Lock lock = new ReentrantLock(false);

    private BigInteger prev = BigInteger.valueOf(-1);
    private BigInteger current = BigInteger.valueOf(1);

    public BigInteger next() {
        lock.lock();
        try {
            BigInteger nextNumber = prev.add(current);
            prev = current;
            current = nextNumber;
            return nextNumber;

        } finally {
            lock.unlock();
        }
    }

}
