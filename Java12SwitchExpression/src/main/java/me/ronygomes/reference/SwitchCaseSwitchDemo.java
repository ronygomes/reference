package me.ronygomes.reference;

public class SwitchCaseSwitchDemo implements SwitchDemo {

    @Override
    public int getSides(Shape shape) {
        int sides;
        switch (shape) {
            case CIRCLE:
                sides = 0;
                break;
            case TRIANGLE:
                sides = 3;
                break;
            case RECTANGLE:
            case TRAPEZIUM:
            case RHOMBUS:
                sides = 4;
                break;
            case PENTAGON:
                sides = 5;
                break;
            case HEXAGON:
                sides = 6;
                break;
            default: throw new IllegalStateException();
        }

        return sides;
    }
}
