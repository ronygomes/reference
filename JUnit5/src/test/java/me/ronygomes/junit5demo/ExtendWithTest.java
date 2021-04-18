package me.ronygomes.junit5demo;

import me.ronygomes.domain.Person;
import me.ronygomes.junit5demo.ext.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LifeCycleCallbackExtension.class)
public class ExtendWithTest {

    // beforeAll, beforeEach, beforeTestExecution, In Test, afterTestExecution, afterEach, afterAll
    @Test
    void testExtentWith() {
        System.out.println("In Test");
    }

    @Test
    @ExtendWith(JohnPersonResolver.class)
    void testParameterResolver(Person person) {
        Assertions.assertEquals(1, person.getId());
        Assertions.assertEquals("John", person.getName());
        Assertions.assertEquals(25, person.getAge());
    }

    // This test will be ignored as TestNameExitExecutionCondition disabled it
    @Test
    @ExtendWith(TestNameExitExecutionCondition.class)
    void testExit() {

    }

    @Test
    @ExtendWith(IgnoreExceptionExecutionExceptionHandler.class)
    void testExceptionResolver() {
        int x = 1 / 0;
    }

    @TestWithJohn
    void testCustomAnnotation(Person person) {
        Assertions.assertEquals(1, person.getId());
        Assertions.assertEquals("John", person.getName());
        Assertions.assertEquals(25, person.getAge());
    }

    @Test
    @ExtendWith(ExtensionStore.class)
    void testExtensionStore() {

    }
}
