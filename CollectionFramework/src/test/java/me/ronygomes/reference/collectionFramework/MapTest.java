package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    @Test
    void testMap() {
        Map<String, Integer> textToNum = new HashMap<>();

        textToNum.put("One", 1);
        textToNum.put("Two", 2);
        textToNum.put("Three", 3);

        assertEquals(3, textToNum.size());
        assertEquals(1, textToNum.get("One"));

        assertFalse(textToNum.containsKey("Four"));
        assertNull(textToNum.get("Four"));
        assertEquals(100, textToNum.getOrDefault("Four", 100));
        assertFalse(textToNum.containsValue(4));

        assertTrue(textToNum.keySet().containsAll(asList("Two", "Three", "One")));
        assertTrue(textToNum.values().containsAll(asList(2, 3, 1)));

        for (Map.Entry<String, Integer> entry : textToNum.entrySet()) {
            entry.setValue(entry.getKey().length() * entry.getValue());
        }

        assertTrue(textToNum.values().containsAll(asList(3, 6, 15)));

        Map<String, Integer> copy = new HashMap<>();
        textToNum.forEach(copy::put);
        assertIterableEquals(textToNum.entrySet(), copy.entrySet());
        assertIterableEquals(textToNum.values(), copy.values());

        textToNum.compute("One", (key, value) -> value + 100);
        assertEquals(103, textToNum.get("One"));

        //textToNum.repl ace()
    }
}
