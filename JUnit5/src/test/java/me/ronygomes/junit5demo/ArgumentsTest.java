package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import java.util.Collections;

public class ArgumentsTest {

    @Test
    void testTestInfo(TestInfo info) {
        Assertions.assertEquals("testTestInfo(TestInfo)", info.getDisplayName());
        Assertions.assertIterableEquals(Collections.emptySet(), info.getTags());

        info.getTestClass().ifPresent(e -> {
            Assertions.assertEquals(ArgumentsTest.class, e);
        });

        info.getTestMethod().ifPresent(m -> {
            Assertions.assertEquals("testTestInfo", m.getName());

        });
    }

    @Test
    void testTestReporter(TestReporter reporter) {
        reporter.publishEntry("1"); // Will be printed value = 1
        reporter.publishEntry("Data", "2"); // Will be printed Data = 2

        // reporter.publishEntry("NullData", null); // Will throw PreconditionViolationException
    }
}
