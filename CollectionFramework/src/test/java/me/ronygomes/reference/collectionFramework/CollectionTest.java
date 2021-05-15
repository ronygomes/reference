package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionTest {

    @Test
    void testCollectionMethods() {
        Collection<Integer> collection = new ArrayList<>(asList(1, 2, 3));

        assertIterableEquals(asList(1, 2, 3), collection);

        collection.add(3);
        assertIterableEquals(asList(1, 2, 3, 3), collection);

        collection.addAll(asList(2, 4));
        assertIterableEquals(asList(1, 2, 3, 3, 2, 4), collection);

        assertTrue(collection.contains(3));
        assertFalse(collection.contains(5));
        assertTrue(collection.containsAll(asList(1, 3, 4)));
        assertTrue(collection.equals(asList(1, 2, 3, 3, 2, 4))); // Object#equals()

        assertFalse(collection.isEmpty());

        List<Integer> ints = new ArrayList<>();
        Iterator<Integer> iterator = collection.iterator();

        while (iterator.hasNext()) {
            ints.add(iterator.next());
        }
        assertIterableEquals(collection, ints);

        int sum = collection.stream().mapToInt(Integer::intValue).sum(); // or partialStream()
        assertEquals(15, sum);

        collection.remove(2);
        assertIterableEquals(asList(1, 3, 3, 2, 4), collection);

        collection.removeAll(asList(3)); // removes all instance of 3
        assertIterableEquals(asList(1, 2, 4), collection);

        collection.removeIf(x -> x > 3);
        assertIterableEquals(asList(1, 2), collection);

        collection.addAll(asList(3, 4, 5)); // 1, 2, 3, 4, 5
        collection.retainAll(asList(1, 3, 5, 7));

        assertIterableEquals(asList(1, 3, 5), collection);
        assertEquals(3, collection.size());

        Object[] objects = collection.toArray();
        Integer[] integers = collection.toArray(new Integer[0]);

        assertArrayEquals(new Object[]{1, 3, 5}, objects);
        assertArrayEquals(new Integer[]{1, 3, 5}, integers);

        collection.clear();
        assertIterableEquals(Collections.emptyList(), collection);
        assertTrue(collection.isEmpty());

    }

    @Test
    void testSpliterator() {
        List<Integer> ints = new ArrayList<>(asList(1, 2, 3, 4, 5));

        Spliterator<Integer> sp1 = ints.spliterator();

        assertFalse(sp1.hasCharacteristics(Spliterator.CONCURRENT));
        assertFalse(sp1.hasCharacteristics(Spliterator.DISTINCT));
        assertFalse(sp1.hasCharacteristics(Spliterator.IMMUTABLE));
        assertFalse(sp1.hasCharacteristics(Spliterator.NONNULL)); // Doesn't guarantee value is NOT NULL
        assertTrue(sp1.hasCharacteristics(Spliterator.ORDERED));
        assertTrue(sp1.hasCharacteristics(Spliterator.SIZED));
        assertFalse(sp1.hasCharacteristics(Spliterator.SORTED));
        assertTrue(sp1.hasCharacteristics(Spliterator.SUBSIZED));

        List<Integer> actualInts1 = new ArrayList<>();

        assertEquals(5, sp1.estimateSize());
        assertEquals(5, sp1.getExactSizeIfKnown());

        sp1.forEachRemaining(actualInts1::add);

        assertEquals(0, sp1.estimateSize());
        assertEquals(0, sp1.getExactSizeIfKnown());

        assertIterableEquals(ints, actualInts1);
        sp1.forEachRemaining(x -> fail()); // Once consumed its empty

        Spliterator<Integer> sp2 = ints.spliterator();
        Spliterator<Integer> sp3 = sp2.trySplit();

        assertEquals(3, sp2.estimateSize()); // [3, 4, 5]
        assertEquals(2, sp3.estimateSize()); // [1,2]

        List<Integer> actualInts2 = new ArrayList<>();
        // tryAdvance() = iterator.hasNext() + doSomething(iterator.next())
        while(sp2.tryAdvance(actualInts2::add))
        assertIterableEquals(asList(3, 4, 5), actualInts2);

        List<Integer> actualInts3 = new ArrayList<>();
        while(sp3.tryAdvance(actualInts3::add));
        assertIterableEquals(asList(1, 2), actualInts3);
    }
}
