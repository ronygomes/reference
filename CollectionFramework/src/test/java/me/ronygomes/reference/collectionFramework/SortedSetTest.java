package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SortedSetTest {

    @Test
    void testSortedSet() {
        SortedSet<Integer> ints = new TreeSet<>(asList(4, 2, 1, 6, 5));

        assertEquals(1, ints.first());
        assertEquals(6, ints.last());

        // 3 is not in ints
        assertIterableEquals(asList(1, 2), ints.headSet(3));
        assertIterableEquals(asList(4, 5, 6), ints.tailSet(3));
        assertIterableEquals(singletonList(4), ints.subSet(3, 5));
    }
}
