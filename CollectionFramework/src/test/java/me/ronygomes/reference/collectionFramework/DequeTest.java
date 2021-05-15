package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class DequeTest {

    /**
     * java.util package's Queue are all unbounded, ie can't have fixed size queue.
     * But java.util.concurrent package's Queue implementations are bounded so safer to use
     * poll, peek & offer methods
     */
    @Test
    void testDeque() {
        Deque<Integer> deque = new ArrayDeque<>();

        assertEquals(0, deque.size());

        deque.addFirst(1); // same as offer() but throws exception if can't add
        deque.offerFirst(2);

        assertIterableEquals(asList(2, 1), deque);

        deque.addLast(3); // same as offerLast() but throws exception if can't add
        deque.offerLast(4);
        deque.offerLast(5);
        deque.offerLast(6);

        assertIterableEquals(asList(2, 1, 3, 4, 5, 6), deque);

        assertEquals(2, deque.removeFirst());
        assertEquals(1, deque.pollFirst());

        assertIterableEquals(asList(3, 4, 5, 6), deque);

        assertEquals(6, deque.removeLast());
        assertEquals(5, deque.pollLast());

        assertIterableEquals(asList(3, 4), deque);

        assertEquals(3, deque.peekFirst());
        assertEquals(3, deque.getFirst());

        assertIterableEquals(asList(3, 4), deque);

        assertEquals(4, deque.peekLast());
        assertEquals(4, deque.getLast());

        assertIterableEquals(asList(3, 4), deque);

        deque.addAll(asList(5, 5, 3, 7, 4));
        assertIterableEquals(asList(3, 4, 5, 5, 3, 7, 4), deque);

        deque.removeFirstOccurrence(5);
        assertIterableEquals(asList(3, 4, 5, 3, 7, 4), deque);

        deque.removeLastOccurrence(4);
        assertIterableEquals(asList(3, 4, 5, 3, 7), deque);
    }
}
