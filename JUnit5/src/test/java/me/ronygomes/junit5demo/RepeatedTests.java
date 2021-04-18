package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import java.util.Arrays;

/**
 * Annotation @RepeatedTest is same as @Test. No need to specify @Test separately
 * Custom message takes 3 placeholder: {displayName}, {currentRepetition}, {totalRepetition}
 */
public class RepeatedTests {

    // Default is SHORT_DISPLAY_NAME: repetition {currentRepetition} of {totalRepetitions}
    @RepeatedTest(value = 5, name = RepeatedTest.SHORT_DISPLAY_NAME)
    void repeatSameTest5Times() {
        Assertions.assertTrue(true);
    }

    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    void repeatSameTest5TimesWithLongMessage() {
        Assertions.assertTrue(true);
    }

    @RepeatedTest(value = 5, name = "{displayName} :: {currentRepetition}@{totalRepetitions}")
    void repeatSameTest5TimesWithCustomMessage() {
        Assertions.assertTrue(true);
    }

    @RepeatedTest(value = 5, name = "{displayName} :: {currentRepetition}@{totalRepetitions}")
    void repeatSameTest5TimesWithCustomMessage(RepetitionInfo rti) {
        Assertions.assertTrue(Arrays.asList(1, 2, 3, 4, 5).contains(rti.getCurrentRepetition()));
    }
}
