package me.ronygomes.reference.iterator;

import java.util.Iterator;
import java.util.Random;

public class RandomNumberGenerator implements Iterable<Integer> {

    private final int size;

    public RandomNumberGenerator(int size) {
        this.size = size;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            private final Random random = new Random();
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Integer next() {
                count++;
                return random.nextInt();
            }
        };
    }
}

