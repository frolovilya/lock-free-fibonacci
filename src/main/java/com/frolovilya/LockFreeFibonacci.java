package com.frolovilya;

import net.jcip.annotations.Immutable;
import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Lock-free thread safe implementation of Fibonacci number generator
 *
 * @see <a href="https://en.wikipedia.org/wiki/Fibonacci_number">Fibonacci number</a>
 */
@ThreadSafe
public class LockFreeFibonacci {

    /**
     * Immutable structure to save state for next numbers generation
     */
    @Immutable
    private class State {
        private final BigInteger current;
        private final BigInteger prev;

        State(BigInteger current, BigInteger prev) {
            this.current = current;
            this.prev = prev;
        }

        BigInteger getCurrent() {
            return current;
        }

        BigInteger getPrev() {
            return prev;
        }
    }

    private final AtomicReference<State> state = new AtomicReference<>(
            new State(BigInteger.valueOf(1), BigInteger.valueOf(-1))
    );

    /**
     * Produces next Fibonacci number without locking.
     * Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
     *
     * @return next Fibonacci number in a sequence
     */
    public BigInteger next() {
        while(true) {
            State currentState = state.get();
            BigInteger prev = currentState.getPrev();
            BigInteger current = currentState.getCurrent();

            // check that the state hasn't been changed to this moment
            if(currentState == state.get()) {
                BigInteger nextNumber = prev.add(current);

                // change state using atomic compareAndSet method
                if (state.compareAndSet(currentState, new State(nextNumber, current)))
                    return nextNumber;
            }
        }
    }

}
