package me.ronygomes.reference.iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberGeneratorTest {

    private final RandomNumberGenerator generator = new RandomNumberGenerator(10);

    @Test
    void testPositiveIteration() {

        List<Integer> result1 = new ArrayList<>();
        generator.forEach(result1::add);

        List<Integer> result2 = new ArrayList<>();
        generator.forEach(result2::add);

        Assertions.assertEquals(10, result1.size());
        Assertions.assertEquals(10, result2.size());

        Assertions.assertNotEquals(result1, result2);
    }
}
