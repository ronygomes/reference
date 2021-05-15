package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class SortedMapTest {

    @Test
    void testSortedSet() {
        SortedMap<Integer, String> map = new TreeMap<>();

        map.put(4, "four");
        map.put(2, "two");
        map.put(1, "one");
        map.put(6, "six");
        map.put(5, "five");

        assertEquals(1, map.firstKey());
        assertEquals(6, map.lastKey());

        // 3 is not in map
        assertIterableEquals(asList(1, 2), map.headMap(3).keySet());
        assertIterableEquals(asList("one", "two"), map.headMap(3).values());
        assertIterableEquals(asList(4, 5, 6), map.tailMap(3).keySet());
        assertIterableEquals(asList("four", "five", "six"), map.tailMap(3).values());
    }
}
