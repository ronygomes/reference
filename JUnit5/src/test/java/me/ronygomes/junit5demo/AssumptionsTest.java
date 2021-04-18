package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Condition can be assumed before or while testing. If assumption doesn't met then
 * tests are aborted but test doesn't fails.
 * <p>
 * Ideal for tested based on environment config, ie. test something only when DEV profile
 * Assumptions.assumeTrue("DEV".equals(AppContext.getProfile()));
 */
public class AssumptionsTest {

    @BeforeEach
    void setup() {
        /*
         * This will be called before each @Test, test will aborted if assumption not met
         */
        Assumptions.assumeFalse(false);
    }

    @Test
    void testAlwaysPass() {
        Assertions.assertTrue(true);
    }

    @Test
    void testAssertionsWillBeSkippedIfAssumptionNotMet() {
        // org.opentest4j.TestAbortedException: Assumption failed: assumption is not tru
        Assumptions.assumeTrue(false);
        Assertions.assertTrue(false);
    }

    @Test
    void testAssumeTrue() {
        Assumptions.assumeTrue(true);
        Assumptions.assumeTrue(true, "Assumption is True");
        Assumptions.assumeTrue(() -> true, "Assumption is True");
        Assumptions.assumeTrue(true, () -> "Assumption is True");
        Assumptions.assumeTrue(() -> true, () -> "Assumption is True");
    }

    @Test
    void testAssumeFalse() {
        Assumptions.assumeFalse(true);
        Assumptions.assumeFalse(true, "Assumption is False");
        Assumptions.assumeFalse(() -> true, "Assumption is False");
        Assumptions.assumeFalse(true, () -> "Assumption is False");
        Assumptions.assumeFalse(() -> true, () -> "Assumption is False");
    }

    @Test
    void testAssumingThat() {
        // assumingThat() runs only tests in Executable. Test after assumption will also run
        Assumptions.assumingThat(false, () -> {
            Assertions.assertEquals(2, 3);
        });

        // Another variation of assumingThat()
        Assumptions.assumingThat(() -> true, () -> {
            Assertions.assertTrue(true);
        });

        Assertions.assertTrue(true); // This test will for `assertTrue(false)'
    }
}
