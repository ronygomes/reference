package me.ronygomes.junit5demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

/*
 * Documentation: https://junit.org/junit5/docs/5.4.2/api/org/junit/jupiter/api/condition/package-summary.html
 */
public class ConditionalTest {

    /**
     * Provided code will be executed in Nashorn engine (Java 8) and will be disabled in return value is true
     * Note: Nashorn engine is removed from a JDK 11
     */
    @Test
    @DisabledIf(value = "3 > 1;") // 3 > 1 = true, so test will be disabled
    void testDisableIf() {

    }

    @Test
    @EnabledIf(value = "3 > 1;") // 3 > 1 = true, so test will be enabled
    void testEnableIf() {

    }

    /*
     * This test will be disabled if $USER environment value is "john"
     */
    @Test
    @DisabledIfEnvironmentVariable(named = "USER", matches = "john")
    void testDisabledIfEnvironmentVariable() {

    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "jane")
    void testEnabledIfEnvironmentVariable() {

    }

    /*
     * This test will be disabled if app.version system property is "v2"
     */
    @Test
    @DisabledIfEnvironmentVariable(named = "app.version", matches = "v2")
    void testDisabledIfSystemProperty() {

    }

    @Test
    @EnabledIfSystemProperty(named = "app.version", matches = "v2")
    void testEnabledIfSystemProperty() {

    }

    // Will be disabled in JRE version is 10
    @Test
    @DisabledOnJre(value = JRE.JAVA_10)
    void testDisabledOnJre() {

    }

    @Test
    @EnabledOnJre(value = JRE.JAVA_10)
    void testEnabledOnJre() {

    }

    @Test
    @DisabledOnOs(OS.LINUX)
    void testDisabledOnOs() {

    }

    @Test
    @DisabledOnOs(OS.MAC)
    void testEnabledOnOs() {

    }
}
