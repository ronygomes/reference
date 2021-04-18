package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Annotation @TestFactory is a like @Test but this method returns list of DynamicNode
 * a. DynamicTest - Can have single Test with multiple assertions
 * b. DynamicContainer - Can have multiple DynamicTest & DynamicContainer.
 * Its an experimental feature. Doesn't work on private & static methods. @BeforeEach and @AfterEach is call only once
 */
public class TestFactoryTest {

    /**
     * Methods of @TestFactory can return Collection, Stream, Iterable, Iterator
     */
    @TestFactory
    Collection<DynamicTest> testDynamicTest() {
        return IntStream.range(1, 6)
                .mapToObj(i -> DynamicTest.dynamicTest(
                        "Test Case: " + i,
                        () -> Assertions.assertTrue(true))
                ).collect(Collectors.toList());
    }

    /**
     * Will run 3 x (1+5) = 18 tests
     */
    @TestFactory
    Collection<DynamicContainer> testDynamicContainer() {
        return IntStream.range(1, 4) // 3
                .mapToObj(i -> DynamicContainer.dynamicContainer(
                        "Container: " + i,
                        Stream.of(
                                DynamicTest.dynamicTest("Dynamic Test ", () -> Assertions.assertTrue(true)), // 1
                                DynamicContainer.dynamicContainer("InnerContainer ", testDynamicTest().stream()) // 5
                        ))).collect(Collectors.toList());
    }
}
