package me.ronygomes.proto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReservedTest {

    @Test
    void testReserved() throws NoSuchMethodException {
        Reseved.Message m = Reseved.Message.newBuilder()
                .setOldField(100)
                .build();

        Assertions.assertEquals(100, m.getOldField());
        Assertions.assertTrue(Reseved.Message.class.getMethod("getOldField").isAnnotationPresent(Deprecated.class));
        Assertions.assertTrue(Reseved.Message.Builder.class.getMethod("setOldField", int.class).isAnnotationPresent(Deprecated.class));
        Assertions.assertTrue(Reseved.Message.Builder.class.getMethod("clearOldField").isAnnotationPresent(Deprecated.class));
    }
}
