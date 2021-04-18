package me.ronygomes.junit5demo;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Default Lifecycle Option. Following option will create new class instance for each test (ie method)
 * Equivalent JVM Option -Djunit.jupiter.testinstance.lifecycle.default=per_method
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class LifecyclePerMethodTest {

    private static final List<String> ACTUAL_PER_METHOD_EXECUTION_ORDER = new ArrayList<>();

    private static String[] PER_METHOD_EXECUTION_ORDER = {
            "BeforeAll",
            "Constructor",
            "BeforeEach",
            "Test1",
            "AfterEach",
            "Constructor",
            "BeforeEach",
            "Test2",
            "AfterEach",
            "AfterAll",
    };

    public LifecyclePerMethodTest() {
        ACTUAL_PER_METHOD_EXECUTION_ORDER.add("Constructor");
    }

    /**
     * Must be `static` as will be call before class creation
     */
    @BeforeAll
    static void setupAll() {
        ACTUAL_PER_METHOD_EXECUTION_ORDER.add("BeforeAll");
    }

    /**
     * Must be `static` as will be called finally after all tests
     */
    @AfterAll
    static void tearDownAll() {
        ACTUAL_PER_METHOD_EXECUTION_ORDER.add("AfterAll");
        Assertions.assertIterableEquals(asList(PER_METHOD_EXECUTION_ORDER),
                ACTUAL_PER_METHOD_EXECUTION_ORDER);
    }

    @BeforeEach
    void setup() {
        ACTUAL_PER_METHOD_EXECUTION_ORDER.add("BeforeEach");
    }

    @AfterEach
    void tearDown() {
        ACTUAL_PER_METHOD_EXECUTION_ORDER.add("AfterEach");
    }

    @Test
    void test1() {
        ACTUAL_PER_METHOD_EXECUTION_ORDER.add("Test1");
    }

    @Test
    void test2() {
        ACTUAL_PER_METHOD_EXECUTION_ORDER.add("Test2");
    }
}
