package me.ronygomes.proto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinaryStoreTest {

    @Test
    void testReadAndWriteBinary() throws IOException {
        Path tempFilePath = Files.createTempFile("person", ".bin");

        AllTypeOuterClass.Person p0 = AllTypeOuterClass.Person.newBuilder()
                .setName("John")
                .setAge(25)
                .build();

        Files.write(tempFilePath, p0.toByteArray());
        Assertions.assertTrue(Files.exists(tempFilePath));

        AllTypeOuterClass.Person p1 = AllTypeOuterClass.Person
                .parseFrom(Files.newInputStream(tempFilePath));

        Assertions.assertEquals("John", p1.getName());
        Assertions.assertEquals(25, p1.getAge());

        Files.deleteIfExists(tempFilePath);
        Assertions.assertTrue(Files.notExists(tempFilePath));
    }
}
