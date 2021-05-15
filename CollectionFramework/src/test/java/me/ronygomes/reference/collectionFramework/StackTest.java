package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    void testLegacyStack() {
        Stack<Integer> stack = new Stack<>();

        assertEquals(0, stack.size());
        assertTrue(stack.empty());

        assertEquals(1, stack.push(1));
        assertEquals(2, stack.push(2));

        assertEquals(2, stack.size());
        assertFalse(stack.empty());

        assertEquals(2, stack.peek());
        assertEquals(2, stack.size());

        assertEquals(2, stack.pop());
        assertEquals(1, stack.size());
    }

    /**
     * java.util.Stack was written in java 1.0. Later in 1.2 collection framework was introduced
     * Problem with stack is it's thread safe and for that its slower
     * In Java 1.6 Deque was introduced (Double Ended Queue) and is recommended to used as Stack
     */
    @Test
    void testStackWithArrayDeque() {
        Deque<Integer> stack = new ArrayDeque<>();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());

        stack.addFirst(1);
        stack.addFirst(2);

        assertEquals(2, stack.size());
        assertFalse(stack.isEmpty());

        assertEquals(2, stack.peekFirst());
        assertEquals(2, stack.size());

        assertEquals(2, stack.pollFirst());
        assertEquals(1, stack.size());
    }
}
