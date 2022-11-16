package me.ronygomes.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VersionTest {

    @Test
    void testForwardCompatibility() throws InvalidProtocolBufferException {
        Version.Version1 v1 = Version.Version1.newBuilder()
                .setExpense(99)
                .setMessage("Hello")
                .setId(50)
                .setType(Version.Version1.Type.TYPE_ORANGE)
                .setData("Deleted Message")
                .build();

        Version.Version2 v2 = Version.Version2.parseFrom(v1.toByteArray());

        Assertions.assertEquals("", v2.getNewMessage());
        Assertions.assertEquals(99, v2.getExpense());
        Assertions.assertEquals("Hello", v2.getOldMessage());
        Assertions.assertEquals(50, v2.getId());

        Assertions.assertNotEquals(v1.getType(), v2.getType());
        Assertions.assertEquals(Version.Version2.Type.UNRECOGNIZED, v2.getType());
    }

    @Test
    void testBackwardCompatibility() throws InvalidProtocolBufferException {
        Version.Version2 v2 = Version.Version2.newBuilder()
                .setNewMessage("New Messages")
                .setExpense(99)
                .setOldMessage("Old Message")
                .setId(50)
                .setType(Version.Version2.Type.TYPE_GREEN)
                .build();

        Version.Version1 v1 = Version.Version1.parseFrom(v2.toByteArray());

        Assertions.assertEquals(99, v1.getExpense());
        Assertions.assertEquals("Old Message", v1.getMessage());
        Assertions.assertEquals(50, v1.getId());

        Assertions.assertNotEquals(v1.getType(), v2.getType());
        Assertions.assertEquals(Version.Version1.Type.UNRECOGNIZED, v1.getType());

        Assertions.assertEquals("", v1.getData());
    }
}
