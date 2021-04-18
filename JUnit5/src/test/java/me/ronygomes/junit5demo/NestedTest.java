package me.ronygomes.junit5demo;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NestedTest {

    private static final List<String> ACTUAL_NESTED_EXECUTION_ORDER = new ArrayList<>();

    private static String[] NESTED_EXECUTION_ORDER = {
            "BeforeEachOuter",
            "OuterTest",
            "AfterEachOuter",

            "BeforeEachOuter",
            "BeforeEachNested1",
            "Nested1Test",
            "AfterEachNested1",
            "AfterEachOuter",

            "BeforeEachOuter",
            "BeforeEachNested1",
            "BeforeEachNested2",
            "Nested2Test",
            "AfterEachNested2",
            "AfterEachNested1",
            "AfterEachOuter",
            "AfterAllOuter",
    };

    @BeforeEach
    void setup() {
        ACTUAL_NESTED_EXECUTION_ORDER.add("BeforeEachOuter");
    }

    @AfterEach
    void tearDown() {
        ACTUAL_NESTED_EXECUTION_ORDER.add("AfterEachOuter");
    }

    @AfterAll
    void tearDownAll() {
        ACTUAL_NESTED_EXECUTION_ORDER.add("AfterAllOuter");
        Assertions.assertIterableEquals(asList(NESTED_EXECUTION_ORDER), ACTUAL_NESTED_EXECUTION_ORDER);
    }

    @Test
    void outerTest() {
        ACTUAL_NESTED_EXECUTION_ORDER.add("OuterTest");
    }

    @Nested
    class Nested1 {

        @BeforeEach
        void setup() {
            ACTUAL_NESTED_EXECUTION_ORDER.add("BeforeEachNested1");
        }

        @AfterEach
        void tearDown() {
            ACTUAL_NESTED_EXECUTION_ORDER.add("AfterEachNested1");
        }

        @Test
        void nested1Test() {
            ACTUAL_NESTED_EXECUTION_ORDER.add("Nested1Test");
        }

        @Nested
        class Nested2 {

            @BeforeEach
            void setup() {
                ACTUAL_NESTED_EXECUTION_ORDER.add("BeforeEachNested2");
            }

            @AfterEach
            void tearDown() {
                ACTUAL_NESTED_EXECUTION_ORDER.add("AfterEachNested2");
            }

            @Test
            void nested2Test() {
                ACTUAL_NESTED_EXECUTION_ORDER.add("Nested2Test");
            }
        }
    }
}
