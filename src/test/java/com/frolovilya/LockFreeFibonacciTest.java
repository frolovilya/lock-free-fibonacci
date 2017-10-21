package com.frolovilya;

import com.devexperts.dxlab.lincheck.LinChecker;
import com.devexperts.dxlab.lincheck.annotations.Operation;
import com.devexperts.dxlab.lincheck.annotations.Reset;
import com.devexperts.dxlab.lincheck.stress.StressCTest;
import org.junit.Test;

import java.math.BigInteger;

/**
 * Testing Lock-free Fibonacci number generator using linearization
 *
 * @see <a href="https://en.wikipedia.org/wiki/Linearizability">Linearizability</a>
 */
// This test uses 3 parallel threads and executes 1-3 operations in each
@StressCTest(iterations = 300, actorsPerThread = {"1:3", "1:3", "1:3"})
public class LockFreeFibonacciTest {

    private LockFreeFibonacci lockFreeFibonacci;

    @Reset
    public void reset() {
        lockFreeFibonacci = new LockFreeFibonacci();
    }

    @Operation
    public BigInteger next() {
        return lockFreeFibonacci.next();
    }

    @Test
    public void test() {
        LinChecker.check(LockFreeFibonacciTest.class);
    }

}
