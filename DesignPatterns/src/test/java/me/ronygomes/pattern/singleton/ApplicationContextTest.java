package me.ronygomes.pattern.singleton;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rony Gomes <rgomes.bd@gmail.com>
 * @since 2015.12.13
 */
public class ApplicationContextTest {

    @Test
    public void shouldGetSameInstance() {
        ApplicationContext context1 = ApplicationContext.getInstance();
        ApplicationContext context2 = ApplicationContext.getInstance();

        assertTrue(context1 == context2);
    }

    @Test
    public void valueShouldBeEqual() {
        ApplicationContext context1 = ApplicationContext.getInstance();
        context1.setConfig("Configuration");

        ApplicationContext context2 = ApplicationContext.getInstance();

        assertEquals(context1.getConfig(), context2.getConfig());
    }
}
