package me.ronygomes.junit5demo.ext;

import org.junit.jupiter.api.extension.*;

public class LifeCycleCallbackExtension implements
        BeforeAllCallback, BeforeEachCallback, BeforeTestExecutionCallback,
        AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("beforeAll");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("beforeEach");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("beforeTestExecution");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("afterTestExecution");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("afterEach");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("afterAll");
    }
}
