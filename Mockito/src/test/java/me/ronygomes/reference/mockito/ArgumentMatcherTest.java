package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * It is ideal to specify exact argument, but can use default argument mather to match against specified type
 * <pre>
 *     Mockito.when(service.square(5)).thenReturn(25));
 *
 *     // Following code is less readable, because 25 will also return 25
 *     // Not big deal as mock data but user may get confuse with method name 'square'
 *     Mockito.when(service.square(Mockito.anyInt())).thenReturn(25));
 * </pre>
 */
public class ArgumentMatcherTest {

    private static final Object MOCK_OBJECT = new Object();

    private ArgumentMatcherService mockService;

    @BeforeEach
    void setup() {
        this.mockService = Mockito.mock(ArgumentMatcherService.class);
    }

    @Test
    void testPrimitiveArgumentMatcher() {
        Mockito.when(mockService.squareInt(Mockito.anyInt())).thenReturn(100);
        Assertions.assertEquals(100, mockService.squareInt(5));
        Assertions.assertEquals(100, mockService.squareInt(10));

        Mockito.when(mockService.squareLong(Mockito.anyLong())).thenReturn(100L);
        Assertions.assertEquals(100L, mockService.squareLong(5));
        Assertions.assertEquals(100L, mockService.squareLong(10));

        Mockito.when(mockService.squareFloat(Mockito.anyFloat())).thenReturn(100F);
        Assertions.assertEquals(100F, mockService.squareFloat(5));
        Assertions.assertEquals(100F, mockService.squareFloat(10));

        Mockito.when(mockService.squareDouble(Mockito.anyDouble())).thenReturn(100D);
        Assertions.assertEquals(100D, mockService.squareDouble(5));
        Assertions.assertEquals(100D, mockService.squareDouble(10));

        Mockito.when(mockService.squareByte(Mockito.anyByte())).thenReturn((byte) 100);
        Assertions.assertEquals(100, mockService.squareByte((byte) 5));
        Assertions.assertEquals(100, mockService.squareByte((byte) 10));

        Mockito.when(mockService.squareShort(Mockito.anyShort())).thenReturn((short) 100);
        Assertions.assertEquals(100, mockService.squareShort((short) 5));
        Assertions.assertEquals(100, mockService.squareShort((short) 10));

        Mockito.when(mockService.upperCaseChar(Mockito.anyChar())).thenReturn('A');
        Assertions.assertEquals('A', mockService.upperCaseChar('a'));
        Assertions.assertEquals('A', mockService.upperCaseChar('b'));

        Mockito.when(mockService.negateBoolean(Mockito.anyBoolean())).thenReturn(true);
        Assertions.assertTrue(mockService.negateBoolean(true));
        Assertions.assertTrue(mockService.negateBoolean(false));
    }

    @Test
    void testCollectionType() {
        Mockito.when(mockService.returnRuntimeIgnoringGivenParam(Mockito.anyCollection())).thenReturn(MOCK_OBJECT);
        Assertions.assertEquals(MOCK_OBJECT, mockService.returnRuntimeIgnoringGivenParam(Collections.singletonList(1)));
        Assertions.assertNull(mockService.returnRuntimeIgnoringGivenParam(null));
    }

    @Test
    void testSet() {
        Mockito.when(mockService.returnRuntimeIgnoringGivenParam(Mockito.anySet())).thenReturn(MOCK_OBJECT);
        Assertions.assertNull(mockService.returnRuntimeIgnoringGivenParam(Collections.singletonList(1)));
        Assertions.assertEquals(MOCK_OBJECT, mockService.returnRuntimeIgnoringGivenParam(new HashSet<>(Collections.singletonList(1))));
    }

    @Test
    void testMap() {
        Mockito.when(mockService.returnRuntimeIgnoringGivenParam(Mockito.anyMap())).thenReturn(MOCK_OBJECT);
        Assertions.assertNull(mockService.returnRuntimeIgnoringGivenParam(Collections.singletonList(1)));
        Assertions.assertEquals(MOCK_OBJECT, mockService.returnRuntimeIgnoringGivenParam(new HashMap<>()));
    }

    @Test
    void testObject() {
        Assertions.assertNull(mockService.returnRuntimeIgnoringGivenParam(new Object()));

        Mockito.when(mockService.returnRuntimeIgnoringGivenParam(Mockito.any())).thenReturn(MOCK_OBJECT);
        Assertions.assertEquals(MOCK_OBJECT, mockService.returnRuntimeIgnoringGivenParam(Runtime.getRuntime()));

        Mockito.when(mockService.returnRuntimeIgnoringGivenParam(Mockito.any())).thenCallRealMethod();
        Assertions.assertEquals(Runtime.getRuntime(), mockService.returnRuntimeIgnoringGivenParam(new Object()));
    }

    @Test
    void testIsNull() {
        Mockito.when(mockService.returnRuntimeIgnoringGivenParam(Mockito.isNull())).thenReturn(MOCK_OBJECT);
        Assertions.assertEquals(MOCK_OBJECT, mockService.returnRuntimeIgnoringGivenParam(null));
        Assertions.assertNull(mockService.returnRuntimeIgnoringGivenParam(new Object()));
    }

    @Test
    void testNotNull() {
        Mockito.when(mockService.returnRuntimeIgnoringGivenParam(Mockito.isNotNull())).thenReturn(MOCK_OBJECT);
        Assertions.assertEquals(MOCK_OBJECT, mockService.returnRuntimeIgnoringGivenParam(new Object()));
        Assertions.assertNull(mockService.returnRuntimeIgnoringGivenParam(null));
    }

    @Test
    void testTypeOf() {
        Mockito.when(mockService.returnRuntimeIgnoringGivenParam(Mockito.isA(IOException.class))).thenReturn(MOCK_OBJECT);
        Assertions.assertEquals(MOCK_OBJECT, mockService.returnRuntimeIgnoringGivenParam(new IOException()));
        Assertions.assertNull(mockService.returnRuntimeIgnoringGivenParam(new Object()));
    }

    /**
     * Mockito tries to best match the argument. Some assertion gets data from previous stubbing
     */
    @Test
    void testString() {
        Mockito.when(mockService.upperCaseString(Mockito.anyString())).thenReturn("Hello!");
        Assertions.assertEquals("Hello!", mockService.upperCaseString("abc"));
        Assertions.assertEquals("Hello!", mockService.upperCaseString("def"));

        Mockito.when(mockService.upperCaseString(Mockito.startsWith("ab"))).thenReturn("ABC");
        Assertions.assertEquals("ABC", mockService.upperCaseString("abc"));
        Assertions.assertEquals("Hello!", mockService.upperCaseString("def"));

        Mockito.when(mockService.upperCaseString(Mockito.endsWith("gh"))).thenReturn("GH");
        Assertions.assertEquals("ABC", mockService.upperCaseString("abcd"));
        Assertions.assertEquals("GH", mockService.upperCaseString("efgh"));

        Mockito.when(mockService.upperCaseString(Mockito.contains("x"))).thenReturn("X");
        Assertions.assertEquals("X", mockService.upperCaseString("axz"));

        Mockito.when(mockService.upperCaseString(Mockito.matches("cat|dog"))).thenReturn("D|C");
        Assertions.assertEquals("D|C", mockService.upperCaseString("cat"));
        Assertions.assertEquals("D|C", mockService.upperCaseString("dog"));
        Assertions.assertEquals("Hello!", mockService.upperCaseString("pizza"));
    }

    @Test
    void testThat() {
        Mockito.when(mockService.squareInt(Mockito.intThat(i -> i % 2 == 0))).thenReturn(100);
        Assertions.assertEquals(0, mockService.squareInt(5));
        Assertions.assertEquals(100, mockService.squareInt(8));
        Assertions.assertEquals(100, mockService.squareInt(10));
    }
}
