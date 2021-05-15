package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    @Test
    void testQueueWithLinkedList() {
        Queue<Integer> queue = new LinkedList<>();

        assertEquals(0, queue.size());

        // Both adds in queue, add() throws IllegalStateException but offer() fails silently
        assertTrue(queue.add(1));
        assertTrue(queue.offer(2));

        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertFalse(queue.contains(3));

        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());

        // Both return next in queue, element() throws NoSuchElementException if empty but peek() fails silently
        assertEquals(1, queue.element());
        assertEquals(1, queue.peek());

        assertEquals(2, queue.size());

        // Both removes from queue, remove() throws NoSuchElementException if empty but poll() fails silently
        assertEquals(1, queue.remove());
        assertEquals(2, queue.poll());

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        assertThrows(NoSuchElementException.class, queue::element);
        assertThrows(NoSuchElementException.class, queue::remove);

        assertNull(queue.peek());
        assertNull(queue.poll());
    }

    @Test
    void testQueueWithArrayDeque() {
        Queue<Integer> queue = new ArrayDeque<>(2);

        assertEquals(0, queue.size());

        // Both adds in queue, add() throws IllegalStateException but offer() fails silently
        assertTrue(queue.add(1));
        assertTrue(queue.offer(2));

        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());

        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertFalse(queue.contains(3));

        assertTrue(queue.add(3));
        assertTrue(queue.offer(4));

        // Both return next in queue, element() throws NoSuchElementException if empty but peek() fails silently
        assertEquals(1, queue.element());
        assertEquals(1, queue.peek());

        assertEquals(4, queue.size());

        // Both removes from queue, remove() throws NoSuchElementException if empty but poll() fails silently
        assertEquals(1, queue.remove());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.remove());
        assertEquals(4, queue.poll());

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());

        assertThrows(NoSuchElementException.class, queue::element);
        assertThrows(NoSuchElementException.class, queue::remove);

        assertNull(queue.peek());
        assertNull(queue.poll());
    }

    @Test
    void testPriorityQueue() {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        assertTrue(queue.add(3));
        assertTrue(queue.add(1));
        assertTrue(queue.add(5));

        assertEquals(5, queue.poll());
        assertEquals(3, queue.poll());
        assertEquals(1, queue.poll());
    }
}
