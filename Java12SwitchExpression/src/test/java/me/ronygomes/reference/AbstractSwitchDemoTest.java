package me.ronygomes.reference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractSwitchDemoTest {

    protected SwitchDemo switchDemo;

    @Test
    void testGetShape() {
        assertEquals(0, switchDemo.getSides(Shape.CIRCLE));
        assertEquals(3, switchDemo.getSides(Shape.TRIANGLE));
        assertEquals(4, switchDemo.getSides(Shape.RECTANGLE));
        assertEquals(4, switchDemo.getSides(Shape.RHOMBUS));
        assertEquals(4, switchDemo.getSides(Shape.TRAPEZIUM));
        assertEquals(5, switchDemo.getSides(Shape.PENTAGON));
        assertEquals(6, switchDemo.getSides(Shape.HEXAGON));
        assertThrows(IllegalStateException.class, () -> switchDemo.getSides(Shape.UNKNOWN));
    }
}
