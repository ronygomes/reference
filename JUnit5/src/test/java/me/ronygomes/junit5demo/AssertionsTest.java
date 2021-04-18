package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.function.ThrowingSupplier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Arrays.asList;


public class AssertionsTest {

    /**
     * This test will go on even if NullPointerException in thrown
     * Each Executable should do its own assertions, throws MultipleFailuresError if failed
     */
    @Test
    void testAssertAll() {
        Executable throwsNullPointer = NullPointerException::new;
        Executable assertTrue = () -> Assertions.assertTrue(true);

        Assertions.assertAll(throwsNullPointer, assertTrue);
        Assertions.assertAll(Stream.of(throwsNullPointer, assertTrue));
        Assertions.assertAll("Common Header", throwsNullPointer, assertTrue);
        Assertions.assertAll("Common Header", Stream.of(throwsNullPointer, assertTrue));
    }

    @Test
    void testAssertArrayEquals() {
        byte byteTen = 10;
        Assertions.assertEquals(byteTen, 10);
        Assertions.assertEquals(byteTen, 10, "byteTen must be equal to 10");
        Assertions.assertEquals(byteTen, 10, () -> "byteTen must be equal to 10");

        char charA = 'A';
        Assertions.assertEquals(charA, 'A');
        Assertions.assertEquals(charA, 'A', "charA must be equal to A");
        Assertions.assertEquals(charA, 'A', () -> "charA must be equal to A");

        double doublePi = 3.14;
        double delta = 0.2;
        Assertions.assertEquals(doublePi, 3.14);
        Assertions.assertEquals(doublePi, 3.14, "PI is equal to 3.14");
        Assertions.assertEquals(doublePi, 3.14, () -> "PI is equal to 3.14");
        Assertions.assertEquals(Math.PI, doublePi, delta);
        Assertions.assertEquals(Math.PI, doublePi, delta, "abs(Math.PI - doublePi) should be less then delta");
        Assertions.assertEquals(Math.PI, doublePi, delta, () -> "abs(Math.PI - doublePi) should be less then delta");


        float floatPi = (float) 3.14;
        float floatDelta = (float) 0.2;
        Assertions.assertEquals(floatPi, (float) 3.14);
        Assertions.assertEquals(floatPi, (float) 3.14, "PI is equal to 3.14");
        Assertions.assertEquals(floatPi, (float) 3.14, () -> "PI is equal to 3.14");
        Assertions.assertEquals((float) Math.PI, floatPi, floatDelta);
        Assertions.assertEquals((float) Math.PI, floatPi, floatDelta, "abs(Math.PI - floatPi) should be less then floatDelta");
        Assertions.assertEquals((float) Math.PI, floatPi, floatDelta, () -> "abs(Math.PI - floatPi) should be less then floatDelta");


        int intTen = 10;
        Assertions.assertEquals(intTen, 10);
        Assertions.assertEquals(intTen, 10, "intTen must be equal to 10");
        Assertions.assertEquals(byteTen, 10, () -> "intTen must be equal to 10");

        long longTen = 10;
        Assertions.assertEquals(longTen, 10);
        Assertions.assertEquals(longTen, 10, "longTen must be equal to 10");
        Assertions.assertEquals(longTen, 10, () -> "longTen must be equal to 10");

        /*
         * Returns true if obj1 == obj2 == null or obj1.equals(obj2)
         */
        Assertions.assertEquals(System.in, System.in);
        Assertions.assertEquals(System.in, System.in, "Objects are equals");
        Assertions.assertEquals(System.in, System.in, () -> "Objects are equals");

        short shortTen = 10;
        Assertions.assertEquals(shortTen, 10);
        Assertions.assertEquals(shortTen, 10, "shortTen must be equal to 10");
        Assertions.assertEquals(shortTen, 10, () -> "shortTen must be equal to 10");

    }

    @Test
    void testAssertIterableEquals() {
        List<String> actual = new ArrayList<>();
        actual.add("One");
        actual.add("Two");

        Assertions.assertIterableEquals(asList("One", "Two"), actual);
        Assertions.assertIterableEquals(asList("One", "Two"), actual, "Message");
        Assertions.assertIterableEquals(asList("One", "Two"), actual, () -> "Message Supplier");
    }

    /**
     * Same as assertIterableEquals() but shows diff. Good for line diff
     */
    @Test
    void testAssertLineMatch() {
        List<String> actual = new ArrayList<>();
        actual.add("One");
        actual.add("Two");

        Assertions.assertLinesMatch(asList("One", "Two"), actual);
    }

    /**
     * Exact opposite of assertEquals(Object, Object). No other variation
     */
    @Test
    void testAssertNotEquals() {
        Assertions.assertNotEquals(System.in, System.err);
        Assertions.assertNotEquals(System.in, System.err, "Objects are equals");
        Assertions.assertNotEquals(System.in, System.err, () -> "Objects are equals");
    }

    @Test
    void testAssertNotNull() {
        Assertions.assertNotNull(System.in);
        Assertions.assertNotNull(System.in, "Non Null Expected");
        Assertions.assertNotNull(System.in, () -> "Non Null Expected");
    }

    /**
     * Checks reference. Return true of obj1 == obj1
     */
    @Test
    void testAssertSame() {
        Assertions.assertSame(System.in, System.in);
        Assertions.assertSame(System.in, System.in, "Reference didn't match");
        Assertions.assertSame(System.in, System.in, () -> "Reference didn't match");
    }

    @Test
    void testAssertNotSame() {
        Assertions.assertNotSame(System.in, System.err);
        Assertions.assertNotSame(System.in, System.err, "Reference matched");
        Assertions.assertNotSame(System.in, System.err, () -> "Reference matched");
    }

    @Test
    void testAssertNull() {
        Assertions.assertNull(null);
        Assertions.assertNull(null, "Null Expected");
        Assertions.assertNull(null, () -> "Null Expected");
    }

    @Test
    void testAssertThrows() {
        Executable e = () -> {
            throw new IllegalStateException();
        };

        Assertions.assertThrows(IllegalStateException.class, e);
        Assertions.assertThrows(IllegalStateException.class, e, "Exception Expected");
        Assertions.assertThrows(IllegalStateException.class, e, () -> "Exception Expected");
    }

    @Test
    void testAssertTimeout() {
        Duration oneSecond = Duration.of(1, SECONDS);
        Executable executable = () -> Thread.sleep(700);
        ThrowingSupplier<String> returnAfter700ms = () -> {
            Thread.sleep(700);
            return "Success";
        };

        Assertions.assertTimeout(oneSecond, executable);
        Assertions.assertTimeout(oneSecond, executable, "Message if not finished within 1000ms");
        Assertions.assertTimeout(oneSecond, executable, () -> "Message if not finished within 1000ms");

        Assertions.assertTimeout(oneSecond, returnAfter700ms);
        Assertions.assertTimeout(oneSecond, returnAfter700ms, "Message if not finished within 1000ms");
        Assertions.assertTimeout(oneSecond, returnAfter700ms, () -> "Message if not finished within 1000ms");
    }

    /**
     * Same as assertTimeout() but kills Thread if not finished within time
     */
    @Test
    void testAssertTimeoutPreemptively() {
        Duration oneSecond = Duration.of(1, SECONDS);
        Executable executable = () -> Thread.sleep(700);
        ThrowingSupplier<String> returnAfter700ms = () -> {
            Thread.sleep(700);
            return "Success";
        };

        Assertions.assertTimeoutPreemptively(oneSecond, executable);
        Assertions.assertTimeoutPreemptively(oneSecond, executable, "Message if not finished within 1000ms");
        Assertions.assertTimeoutPreemptively(oneSecond, executable, () -> "Message if not finished within 1000ms");

        Assertions.assertTimeoutPreemptively(oneSecond, returnAfter700ms);
        Assertions.assertTimeoutPreemptively(oneSecond, returnAfter700ms, "Message if not finished within 1000ms");
        Assertions.assertTimeoutPreemptively(oneSecond, returnAfter700ms, () -> "Message if not finished within 1000ms");
    }

    @Test
    void testAssertFalse() {
        Assertions.assertFalse(false);
        Assertions.assertFalse(false, "Message printed when test fails");
        // message parameter is java.util.function.Supplier<String>
        Assertions.assertFalse(false, () -> "Message printed when test fails");

        // Takes a java.util.function.BooleanSupplier
        Assertions.assertFalse(() -> false);
        Assertions.assertFalse(() -> false, "Message printed when test fails");
        Assertions.assertFalse(() -> false, () -> "Message printed when test fails");
    }

    @Test
    void testAssertTrue() {
        Assertions.assertTrue(true);
        Assertions.assertTrue(true, "Message printed when test fails");
        // message parameter is java.util.function.Supplier<String>
        Assertions.assertTrue(true, () -> "Message printed when test fails");

        // Takes a java.util.function.BooleanSupplier
        Assertions.assertTrue(() -> true);
        Assertions.assertTrue(() -> true, "Message printed when test fails");
        Assertions.assertTrue(() -> true, () -> "Message printed when test fails");
    }

    /**
     * Following test will always fail
     * When first assertions fail, other assertion will be skipped for this test
     * <p>
     * fail() is used for testing impossible case
     * Stream.of().map(entry -> fail("Won't come here, as Stream.of() called with no value");
     */
    @Test
    @Disabled
    void testFail() {
        // fail() returns a generic value, so that it can be used with lambda expression with return value
        Object failValue = Assertions.fail();

        Assertions.fail("Message printed when test fails");
        Assertions.fail(new IllegalStateException());
        Assertions.fail("Message printed when test fails", new IllegalStateException());
        Assertions.fail(() -> "Message printed when test fails");
    }
}