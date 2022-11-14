package me.ronygomes.proto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImporterTest {

    @Test
    void testImporter() {
        Importer.Token token = Importer.Token.newBuilder()
                .setId(100)
                .setHello(
                        Imported.Hello.newBuilder()
                                .setLang("en")
                                .setMessage("Hello World")
                                .build()
                )
                .build();

        Assertions.assertEquals(100, token.getId());
        Assertions.assertEquals("en", token.getHello().getLang());
        Assertions.assertEquals("Hello World", token.getHello().getMessage());
    }
}
