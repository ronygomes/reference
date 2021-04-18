package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TagTest {

    @Tag("tag1")
    @Test
    void testTag1() {
        Assertions.assertTrue(true);
    }

    @Tag("tag2")
    @Test
    void testTag2() {
        Assertions.assertTrue(true);
    }

    @Tag("tag3")
    @Test
    void testTag3() {
        Assertions.assertTrue(true);
    }

}
