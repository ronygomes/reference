package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * DisplayName annotation is used to printing space separated test name.
 * Along with @Nested is can be used to write BDD style test
 * <p>
 * Be default IDEA tests using gradle for gradle project. Need to change configuration
 * https://stackoverflow.com/questions/59012129/why-isnt-displayname-working-for-me-in-junit-5
 */
@DisplayName("Given Assertions.assertTrue() method")
public class DisplayNameTest {

    @DisplayName("When `true` is given as parameter")
    @Nested
    class NestedDisplayNameTest {

        @DisplayName("Then it shouldn't complain")
        @Test
        void displayName() {
            Assertions.assertTrue(true);
        }
    }
}
