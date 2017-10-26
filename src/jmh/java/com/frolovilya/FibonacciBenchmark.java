package com.frolovilya;

import org.openjdk.jmh.annotations.*;

import java.math.BigInteger;

/**
 * Comparing blocking, non-blocking and thread local algorithms throughput.
 * Check jmh.gradle for JMH configuration.
 */
public class FibonacciBenchmark {

    @State(Scope.Benchmark)
    public static class BlockingFibonacciState {
        volatile BlockingFibonacciImpl blockingFibonacci = new BlockingFibonacciImpl();

        @Setup(Level.Iteration)
        public void reset() {
            blockingFibonacci = new BlockingFibonacciImpl();
        }
    }

    @State(Scope.Benchmark)
    public static class LockFreeFibonacciState {
        volatile LockFreeFibonacci lockFreeFibonacci = new LockFreeFibonacci();

        @Setup(Level.Iteration)
        public void reset() {
            lockFreeFibonacci = new LockFreeFibonacci();
        }
    }

    @State(Scope.Benchmark)
    public static class ThreadLocalFibonacciState {
        volatile ThreadLocalFibonacciImpl threadLocalFibonacci = new ThreadLocalFibonacciImpl();

        @Setup(Level.Iteration)
        public void reset() {
            threadLocalFibonacci = new ThreadLocalFibonacciImpl();
        }
    }

    @Benchmark
    public BigInteger testLockFree(LockFreeFibonacciState state) {
        return state.lockFreeFibonacci.next();
    }

    @Benchmark
    public BigInteger testBlocking(BlockingFibonacciState state) {
        return state.blockingFibonacci.next();
    }

    @Benchmark
    public BigInteger testThreadLocal(ThreadLocalFibonacciState state) {
        return state.threadLocalFibonacci.next();
    }


}
