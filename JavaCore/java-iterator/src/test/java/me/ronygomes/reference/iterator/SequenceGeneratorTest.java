package me.ronygomes.reference.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class SequenceGeneratorTest {

    private SequenceGenerator generator;

    @Test
    void testForwardIteration() {
         generator = new SequenceGenerator(1, 10, 2);

         List<Integer> result = new ArrayList<>();
         generator.forEach(result::add);

        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals(Arrays.asList(1, 3, 5, 7, 9), result);
    }

    @Test
    void testBackwardIteration() {
        generator = new SequenceGenerator(1, 10, 2);
        ListIterator<Integer> iterator = (ListIterator<Integer>) generator.iterator();

        iterator.forEachRemaining((__) -> {});
        List<Integer> result = new ArrayList<>();
        while (iterator.hasPrevious()) {
            result.add(iterator.previous());
        }

        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals(Arrays.asList(9, 7, 5, 3, 1), result);
    }
}
