package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface InterfaceExample {

    boolean isTrue();

    @Test
    default void testMethodToAlwaysRun() {
        Assertions.assertNotEquals(1, 2);
    }

    @Test
    default void testMethodDependentOnChild() {
        Assertions.assertTrue(isTrue());
    }
}
