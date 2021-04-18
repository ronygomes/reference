package me.ronygomes.junit5demo.ext;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

/**
 * JUnit doesn't guarantee the creation order of class, so extensions are expected to be stateless
 * But if needed to store date
 */
public class ExtensionStore implements BeforeEachCallback, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE
            = ExtensionContext.Namespace.create("me", "ronygomes", "junit5demo");

    private static final String USER_NAME_KEY = "USER_NAME";

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        context.getStore(NAMESPACE).put(USER_NAME_KEY, "John");

        /*
         * Following will be printed for this code
         *
         * Context Type: org.junit.jupiter.engine.descriptor.MethodExtensionContext
         * Context Type: org.junit.jupiter.engine.descriptor.ClassExtensionContext
         * Context Type: org.junit.jupiter.engine.descriptor.JupiterEngineExtensionContext
         *
         * JUnit has 3 LeverContext and each context can store data
         * If stored in MethodExtensionContext#store() can only get in method
         */
        Optional<ExtensionContext> currentContext = Optional.of(context);
        while (currentContext.isPresent()) {
            System.out.println("Context Type: " + currentContext.get().getClass().getTypeName());
            currentContext = currentContext.get().getParent();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("Read from store: " + context.getStore(NAMESPACE).get(USER_NAME_KEY));

    }
}
