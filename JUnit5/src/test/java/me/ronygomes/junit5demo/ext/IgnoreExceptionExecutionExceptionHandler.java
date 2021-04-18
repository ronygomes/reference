package me.ronygomes.junit5demo.ext;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class IgnoreExceptionExecutionExceptionHandler implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context,
                                             Throwable throwable) throws Throwable {

        System.out.println("Following Exception was ignored: " + throwable.getClass().getCanonicalName());
    }
}
