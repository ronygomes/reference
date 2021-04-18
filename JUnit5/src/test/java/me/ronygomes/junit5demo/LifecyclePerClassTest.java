package me.ronygomes.junit5demo;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Following option will create single class instance for all test
 * Equivalent JVM Option -Djunit.jupiter.testinstance.lifecycle.default=per_class
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LifecyclePerClassTest {

    private static final List<String> ACTUAL_PER_CLASS_EXECUTION_ORDER = new ArrayList<>();

    private static String[] EXPECTED_PER_CLASS_EXECUTION_ORDER = {
            "Constructor",
            "BeforeAll",
            "BeforeEach",
            "Test1",
            "AfterEach",
            "BeforeEach",
            "Test2",
            "AfterEach",
            "AfterAll",
    };

    public LifecyclePerClassTest() {
        ACTUAL_PER_CLASS_EXECUTION_ORDER.add("Constructor");
    }

    /**
     * `static` not required as class will be created first
     */
    @BeforeAll
    void setupAll() {
        ACTUAL_PER_CLASS_EXECUTION_ORDER.add("BeforeAll");
    }

    /**
     * `static` not required as class will be created first
     */
    @AfterAll
    void tearDownAll() {
        ACTUAL_PER_CLASS_EXECUTION_ORDER.add("AfterAll");
        Assertions.assertIterableEquals(asList(EXPECTED_PER_CLASS_EXECUTION_ORDER),
                ACTUAL_PER_CLASS_EXECUTION_ORDER);
    }

    @BeforeEach
    void setup() {
        ACTUAL_PER_CLASS_EXECUTION_ORDER.add("BeforeEach");
    }

    @AfterEach
    void tearDown() {
        ACTUAL_PER_CLASS_EXECUTION_ORDER.add("AfterEach");
    }

    @Test
    void test1() {
        ACTUAL_PER_CLASS_EXECUTION_ORDER.add("Test1");
    }

    @Test
    void test2() {
        ACTUAL_PER_CLASS_EXECUTION_ORDER.add("Test2");
    }
}
