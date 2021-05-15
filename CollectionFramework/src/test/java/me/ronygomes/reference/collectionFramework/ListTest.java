package me.ronygomes.reference.collectionFramework;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Along with all the methods defined in java.util.Collection, List interface also
 * providers following methods
 */
public class ListTest {

    @Test
    void testListPositionalAccess() {
        List<Integer> ints = new ArrayList<>(asList(1, 2, 3, 4, 5));

        assertEquals(1, ints.get(0));

        ints.set(3, 2);
        assertIterableEquals(asList(1, 2, 3, 2, 5), ints);

        ints.add(2, 10);
        assertIterableEquals(asList(1, 2, 10, 3, 2, 5), ints);

        ints.addAll(4, asList(100, 101));
        assertIterableEquals(asList(1, 2, 10, 3, 100, 101, 2, 5), ints);

        int num100 = ints.get(4);
        Integer num100Object = ints.get(4);

        // invokes List#remove(index)
        assertThrows(IndexOutOfBoundsException.class, () -> ints.remove(num100));

        ints.remove(num100Object); // invokes Collection#remove(Object)
        assertIterableEquals(asList(1, 2, 10, 3, 101, 2, 5), ints);

    }

    @Test
    void testListSearch() {
        List<Integer> ints = new ArrayList<>(asList(1, 2, 3, 2, 5));

        assertEquals(1, ints.indexOf(2));
        assertEquals(3, ints.lastIndexOf(2));
    }

    @Test
    void testListIterator() {
        List<Integer> ints = new ArrayList<>(asList(1, 2, 3));

        List<Integer> list1 = new ArrayList<>();
        for (ListIterator<Integer> it = ints.listIterator(); it.hasNext(); ) {
            list1.add(it.next());
        }

        assertIterableEquals(asList(1, 2, 3), list1);

        List<Integer> list2 = new ArrayList<>();
        for (ListIterator<Integer> it = ints.listIterator(ints.size()); it.hasPrevious(); ) {
            list2.add(it.previous());
        }
        assertIterableEquals(asList(3, 2, 1), list2);
    }

    @Test
    void testSublist() {
        List<Integer> ints = new ArrayList<>(asList(1, 2, 3, 4, 5));
        List<Integer> subs = ints.subList(1, 4);

        assertEquals(5, ints.size());
        assertEquals(3, subs.size());

        assertSame(ints.get(1), subs.get(0));
        assertSame(ints.get(2), subs.get(1));
        assertSame(ints.get(3), subs.get(2));

        subs.clear();

        assertEquals(2, ints.size());
        assertEquals(0, subs.size());
    }
}
