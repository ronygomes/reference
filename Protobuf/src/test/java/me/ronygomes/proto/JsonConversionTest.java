package me.ronygomes.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonConversionTest {

    private static final String JSON_OUTPUT = String.join(
            System.lineSeparator(),
            new String[]{"{", "  \"value\": \"Hello\"", "}"}
    );

    @Test
    void testConvertToJson() throws InvalidProtocolBufferException {
        OptionData data = OptionData.newBuilder().setValue("Hello").build();
        String jsonString = JsonFormat.printer().print(data);

        Assertions.assertEquals(JSON_OUTPUT, jsonString);
    }

    @Test
    void testConvertFromJson() throws InvalidProtocolBufferException {
        OptionData.Builder builder = OptionData.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(JSON_OUTPUT, builder);

        OptionData data = builder.build();
        Assertions.assertEquals("Hello", data.getValue());
    }
}
