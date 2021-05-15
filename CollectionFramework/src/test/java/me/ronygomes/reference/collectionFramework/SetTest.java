package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetTest {

    /**
     * Set interface doesn't have any own methods, all are inherited from java.util.Collection
     */
    @Test
    void testSetOperations() {
        Set<Integer> set1 = new HashSet<>(asList(1, 2, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(asList(4, 8, 9, 5));

        assertEquals(5, set1.size()); // Doesn't keeps duplicate

        // Coping set1 as its modifies caller object
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        assertTrue(union.containsAll(asList(1, 2, 3, 4, 5, 8, 9)));

        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        assertTrue(intersection.containsAll(asList(4, 5)));

        Set<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        assertTrue(difference.containsAll(asList(1, 2, 3)));

    }
}
