package me.ronygomes.reference.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SequenceGenerator implements Iterable<Integer> {

    private final int start;
    private final int end;
    private final int increment;

    public SequenceGenerator(int start, int end, int increment) {
        this.start = start;
        this.end = end;
        this.increment = increment;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SequenceGeneratorListIterator(start, end, increment);
    }

    /*
     * This ListIterator implementation is very poorly designed,
     * but enough to understand basic workflow
     */
    static class SequenceGeneratorListIterator implements ListIterator<Integer> {
        private final List<Integer> sequence;
        private int index;

        public SequenceGeneratorListIterator(int start, int end, int increment) {
            this.sequence = new ArrayList<>();
            this.index = 0;

            for (int value = start; value < end; value += increment) {
                sequence.add(value);
            }
        }

        @Override
        public boolean hasNext() {
            return index < sequence.size();
        }

        @Override
        public Integer next() {
            if (isOutOfBound()) {
                index = 0;
            }
            int element = sequence.get(index);
            index++;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return index >= 0;
        }

        @Override
        public Integer previous() {
            if (isOutOfBound()) {
                index = sequence.size() - 1;
            }
            int element = sequence.get(index);
            index--;
            return element;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(Integer integer) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(Integer integer) {
            throw new UnsupportedOperationException();
        }

        boolean isOutOfBound() {
            return index < 0 || index == sequence.size();
        }
    }
}

