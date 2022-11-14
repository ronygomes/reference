package me.ronygomes.proto;

import com.google.protobuf.ByteString;
import me.ronygomes.proto.AllTypeOuterClass.AllType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class AllTypeOuterClassTest {

    private static final int TEST_INT_VALUE = 99;
    private static final int TEST_INT_VALUE2 = 100;
    private static final long TEST_LONG_VALUE = 9999;
    private static final float TEST_FLOAT_VALUE = 99.99F;
    private static final double TEST_DOUBLE_VALUE = 9999.99F;

    private static final boolean TEST_BOOLEAN_VALUE = true;

    private static final String TEST_STRING_VALUE = "Hello";
    private static final String TEST_STRING_VALUE1 = "One";
    private static final String TEST_STRING_VALUE2 = "Two";

    private static final ByteString TEST_BYTE_VALUE = ByteString.copyFrom(TEST_STRING_VALUE.getBytes());

    private final AllType DATA = AllType.newBuilder()
            .setTypeInt32(TEST_INT_VALUE)
            .setTypeInt64(TEST_LONG_VALUE)
            .setTypeSint32(TEST_INT_VALUE)
            .setTypeSint64(TEST_LONG_VALUE)
            .setTypeUint32(TEST_INT_VALUE)
            .setTypeUint64(TEST_LONG_VALUE)
            .setTypeFixed32(TEST_INT_VALUE)
            .setTypeFixed64(TEST_LONG_VALUE)
            .setTypeSfixed32(TEST_INT_VALUE)
            .setTypeSfixed64(TEST_LONG_VALUE)
            .setTypeFloat(TEST_FLOAT_VALUE)
            .setTypeDouble(TEST_DOUBLE_VALUE)
            .setTypeBool(TEST_BOOLEAN_VALUE)
            .setTypeBytes(TEST_BYTE_VALUE)
            .setTypeString(TEST_STRING_VALUE)
            .putTypeMap(TEST_INT_VALUE, TEST_STRING_VALUE1)
            .putTypeMap(TEST_INT_VALUE2, TEST_STRING_VALUE2)
            .setTypeOneofOne(TEST_STRING_VALUE)
            .addAllTypeRepeated(Arrays.asList(TEST_STRING_VALUE1, TEST_STRING_VALUE2))
            .setTypeEnum(AllTypeOuterClass.Color.COLOR_BLUE)
            .build();

    @Test
    void testNumbers() {
        Assertions.assertEquals(TEST_INT_VALUE, DATA.getTypeInt32());
        Assertions.assertEquals(TEST_LONG_VALUE, DATA.getTypeInt64());

        Assertions.assertEquals(TEST_INT_VALUE, DATA.getTypeSint32());
        Assertions.assertEquals(TEST_LONG_VALUE, DATA.getTypeSint64());

        Assertions.assertEquals(TEST_INT_VALUE, DATA.getTypeUint32());
        Assertions.assertEquals(TEST_LONG_VALUE, DATA.getTypeUint64());

        Assertions.assertEquals(TEST_INT_VALUE, DATA.getTypeFixed32());
        Assertions.assertEquals(TEST_LONG_VALUE, DATA.getTypeFixed64());

        Assertions.assertEquals(TEST_INT_VALUE, DATA.getTypeSfixed32());
        Assertions.assertEquals(TEST_LONG_VALUE, DATA.getTypeSfixed64());

        Assertions.assertEquals(TEST_FLOAT_VALUE, DATA.getTypeFloat());
        Assertions.assertEquals(TEST_DOUBLE_VALUE, DATA.getTypeDouble());
    }

    @Test
    void testBoolean() {
        Assertions.assertTrue(DATA.getTypeBool());
    }

    @Test
    void testBytes() {
        Assertions.assertEquals(TEST_STRING_VALUE, DATA.getTypeBytes().toStringUtf8());
        Assertions.assertTrue(ByteString.class.isAssignableFrom(DATA.getTypeBytes().getClass()));
    }

    @Test
    void testString() {
        Assertions.assertEquals(TEST_STRING_VALUE, DATA.getTypeString());
    }

    @Test
    void testMap() {
        Map<Integer, String> typeMap = DATA.getTypeMapMap();
        Assertions.assertEquals(2, DATA.getTypeMapCount());
        Assertions.assertEquals(2, typeMap.size());

        Assertions.assertTrue(DATA.containsTypeMap(TEST_INT_VALUE));
        Assertions.assertTrue(DATA.containsTypeMap(TEST_INT_VALUE2));
        Assertions.assertFalse(DATA.containsTypeMap(9999));

        Assertions.assertEquals(TEST_STRING_VALUE1, typeMap.get(TEST_INT_VALUE));
        Assertions.assertEquals(TEST_STRING_VALUE2, typeMap.get(TEST_INT_VALUE2));
    }

    @Test
    void testOneOf() {
        Assertions.assertEquals(TEST_STRING_VALUE, DATA.getTypeOneofOne());
        Assertions.assertEquals("", DATA.getTypeOneofTwo());

        AllType at1 = AllType.newBuilder()
                .setTypeOneofOne("1")
                .setTypeOneofTwo("2")
                .build();

        Assertions.assertEquals("", at1.getTypeOneofOne());
        Assertions.assertEquals("2", at1.getTypeOneofTwo());

        AllType at2 = AllType.newBuilder()
                .setTypeOneofTwo("2")
                .setTypeOneofOne("1")
                .build();

        Assertions.assertEquals("", at2.getTypeOneofTwo());
        Assertions.assertEquals("1", at2.getTypeOneofOne());
    }

    @Test
    void testEnum() {
        Assertions.assertSame(AllTypeOuterClass.Color.COLOR_BLUE, DATA.getTypeEnum());
    }

    @Test
    void testRepeated() {
        Assertions.assertIterableEquals(Arrays.asList(TEST_STRING_VALUE1, TEST_STRING_VALUE2), DATA.getTypeRepeatedList());
    }

    @Test
    void testCustom() {
        AllTypeOuterClass.Person person = AllTypeOuterClass.Person.newBuilder()
                .setName("John")
                .setAge(25)
                .build();

        AllType at = AllType.newBuilder()
                .setTypeCustom(person)
                .build();

        Assertions.assertEquals("John", at.getTypeCustom().getName());
        Assertions.assertEquals(25, at.getTypeCustom().getAge());
    }

    @Test
    void testDefaultValue() {
        AllType at = AllType.newBuilder().build();

        Assertions.assertEquals(0, at.getTypeInt32());
        Assertions.assertEquals(0, at.getTypeInt64());

        Assertions.assertEquals(0, at.getTypeSint32());
        Assertions.assertEquals(0, at.getTypeSint64());

        Assertions.assertEquals(0, at.getTypeUint32());
        Assertions.assertEquals(0, at.getTypeUint64());

        Assertions.assertEquals(0, at.getTypeUint32());
        Assertions.assertEquals(0, at.getTypeUint64());

        Assertions.assertEquals(0, at.getTypeFixed32());
        Assertions.assertEquals(0, at.getTypeFixed64());

        Assertions.assertEquals(0, at.getTypeSfixed32());
        Assertions.assertEquals(0, at.getTypeSfixed64());

        Assertions.assertEquals(0, at.getTypeFloat());
        Assertions.assertEquals(0, at.getTypeDouble());

        Assertions.assertFalse(at.getTypeBool());

        Assertions.assertEquals(ByteString.EMPTY, at.getTypeBytes());
        Assertions.assertEquals("", at.getTypeString());
        Assertions.assertEquals(0, at.getTypeMapCount());

        Assertions.assertEquals("", at.getTypeOneofOne());
        Assertions.assertEquals("", at.getTypeOneofTwo());

        Assertions.assertIterableEquals(Collections.emptyList(), at.getTypeRepeatedList());
        Assertions.assertSame(AllTypeOuterClass.Color.COLOR_UNSPECIFIED, at.getTypeEnum());
    }
}