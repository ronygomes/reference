package me.ronygomes.proto;

import com.google.protobuf.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WellKnownTypeTest {

    @Test
    void testAny() throws InvalidProtocolBufferException {
        AllTypeOuterClass.Person p = AllTypeOuterClass.Person.newBuilder().setName("John").setAge(25).build();

        Any a = Any.newBuilder()
                .setTypeUrl("https://ronygomes.me/proto/me.ronygomes.Person") // Convention is URL/uniqueName
                .setValue(p.toByteString())
                .build();

        AllTypeOuterClass.Person p0 = AllTypeOuterClass.Person.parseFrom(a.getValue());
        Assertions.assertNotSame(p, p0);
        Assertions.assertEquals(p, p0);
    }

    @Test
    void testDescriptor() {
        Descriptors.Descriptor d = OptionData.getDefaultInstance().getDescriptorForType();

        Assertions.assertEquals("me.ronygomes.proto.OptionData", d.getFullName());
        Assertions.assertEquals("OptionData", d.getName());
        Assertions.assertEquals(1, d.getFields().size());

        Descriptors.FieldDescriptor value = d.getFields().get(0);
        Assertions.assertEquals("me.ronygomes.proto.OptionData.value", value.getFullName());
        Assertions.assertEquals("value", value.getName());

        Descriptors.FieldDescriptor.Type type = value.getType();
        Assertions.assertSame(Descriptors.FieldDescriptor.Type.STRING, type);
    }

    @Test
    void testDuration() {
        com.google.protobuf.Duration dg = Duration.newBuilder().setSeconds(1800).setNanos(500).build();
        java.time.Duration dj = java.time.Duration.ofSeconds(dg.getSeconds(), dg.getNanos());

        java.time.Duration dj0 = java.time.Duration.ofSeconds(1800);
        java.time.Duration dj1 = java.time.Duration.ofSeconds(1801);

        Assertions.assertEquals(500, dj.compareTo(dj0));
        Assertions.assertEquals(-1, dj.compareTo(dj1));
    }

    @Test
    void testEmpty() {
        // A generic empty message that you can re-use to avoid defining duplicated empty messages
        // service Foo {
        //      rpc Bar(google.protobuf.Empty) returns (google.protobuf.Empty);
        // }

        Empty e = Empty.newBuilder().build();
        Assertions.assertNotNull(e);
    }

    @Test
    void testSourceContext() {
        SourceContext sc = SourceContext.newBuilder().setFileName("option.proto").build();
        WellKnownType.Types data = WellKnownType.Types.newBuilder().setTypeSourceContext(sc).build();

        Assertions.assertSame(sc, data.getTypeSourceContext());
    }

    @Test
    void testStruct() {
        // Used to represent struct{}, internally it uses map<string, value>
        Struct struct = Struct.newBuilder()
                .putFields("name", Value.newBuilder().setStringValue("John").build())
                .putFields("age", Value.newBuilder().setNumberValue(25).build())
                .build();

        Assertions.assertEquals(2, struct.getFieldsCount());
        Assertions.assertTrue(struct.containsFields("name"));
        Assertions.assertTrue(struct.containsFields("age"));
        Assertions.assertFalse(struct.containsFields("dummy"));
    }

    @Test
    void testTimestamp() {
        // Both Timestamp and Duration has seconds & nanos but usage will be different and application specific
        Timestamp t = Timestamp.newBuilder().setSeconds(18343400).setNanos(500).build();

        Assertions.assertEquals(18343400, t.getSeconds());
        Assertions.assertEquals(500, t.getNanos());
    }

    @Test
    void testWrapper() {
        WellKnownType.Types t = WellKnownType.Types.newBuilder()
                .setTypeWrapperDouble(DoubleValue.newBuilder().setValue(1).build())
                .setTypeWrapperFloat(FloatValue.newBuilder().setValue(2).build())
                .setTypeWrapperInt64(Int64Value.newBuilder().setValue(3).build())
                .setTypeWrapperUint64(UInt64Value.newBuilder().setValue(4).build())
                .setTypeWrapperInt32(Int32Value.newBuilder().setValue(5).build())
                .setTypeWrapperUint32(UInt32Value.newBuilder().setValue(6).build())
                .setTypeWrapperBool(BoolValue.newBuilder().setValue(true).build())
                .setTypeWrapperString(StringValue.newBuilder().setValue("Hello").build())
                .setTypeWrapperBytes(BytesValue.newBuilder().setValue(ByteString.copyFrom("Hello".getBytes())).build())
                .build();

        Assertions.assertEquals(1, t.getTypeWrapperDouble().getValue());
        Assertions.assertEquals(2, t.getTypeWrapperFloat().getValue());
        Assertions.assertEquals(3, t.getTypeWrapperInt64().getValue());
        Assertions.assertEquals(4, t.getTypeWrapperUint64().getValue());
        Assertions.assertEquals(5, t.getTypeWrapperInt32().getValue());
        Assertions.assertEquals(6, t.getTypeWrapperUint32().getValue());
        Assertions.assertTrue(t.getTypeWrapperBool().getValue());
        Assertions.assertEquals("Hello", t.getTypeWrapperString().getValue());
        Assertions.assertArrayEquals(new byte[]{'H', 'e', 'l', 'l', 'o'},
                t.getTypeWrapperBytes().getValue().toByteArray());
    }
}
