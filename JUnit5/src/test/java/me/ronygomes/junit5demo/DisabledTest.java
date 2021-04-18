package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DisabledTest {

    /**
     * Used with test method is not yet complete or temporary disabled as slow to run
     */
    @Test
    @Disabled
    void testDisabled() {
        Assertions.fail("This test will fail if not Disabled");
    }
}
