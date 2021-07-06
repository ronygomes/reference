package me.ronygomes.reference;

public class SwitchExpressionSwitchDemo implements SwitchDemo {

    @Override
    public int getSides(Shape shape) {
        int sides = switch(shape) {
            case CIRCLE -> 0;
            case TRIANGLE -> 3;
            case RECTANGLE, TRAPEZIUM, RHOMBUS -> 4;
            case PENTAGON -> 5;
            case HEXAGON -> 6;
            default -> throw new IllegalStateException();
        };

        return sides;
    }
}
