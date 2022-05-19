package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;

import java.io.IOException;
import java.math.BigInteger;

public class ReturnValueStubbingTest {

    private static final String INVALID_STRING_RETURN_VALUE = "NaN";
    private static final int INVALID_INT_RETURN_VALUE = Integer.MAX_VALUE;
    private CoreService mockService;

    @BeforeEach
    void setup() {
        this.mockService = Mockito.mock(CoreService.class);
    }

    @Test
    void testWithoutStubbingReturnsDefaultValue() {
        Assertions.assertNull(mockService.convertToString(BigInteger.TEN.intValue()));
        Assertions.assertEquals(0, mockService.addTen(BigInteger.TEN.intValue()));
    }

    @Test
    void testStubbingReturnsProvidedValue() {
        Mockito.when(mockService.convertToString(BigInteger.TEN.intValue())).thenReturn(INVALID_STRING_RETURN_VALUE);
        Assertions.assertEquals(INVALID_STRING_RETURN_VALUE, mockService.convertToString(BigInteger.TEN.intValue()));

        Mockito.when(mockService.addTen(BigInteger.TEN.intValue())).thenReturn(INVALID_INT_RETURN_VALUE);
        Assertions.assertEquals(INVALID_INT_RETURN_VALUE, mockService.addTen(BigInteger.TEN.intValue()));
    }

    @Test
    void testStubbingReturnsMethodInvokedValue() {
        Mockito.when(mockService.convertToString(BigInteger.TEN.intValue())).thenCallRealMethod();
        Assertions.assertEquals("10", mockService.convertToString(BigInteger.TEN.intValue()));

        Mockito.when(mockService.addTen(BigInteger.TEN.intValue())).thenCallRealMethod();
        Assertions.assertEquals(20, mockService.addTen(BigInteger.TEN.intValue()));
    }

    @Test
    void testPartialStubbingThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> Mockito.when(mockService.convertToString(Mockito.any())));
        Assertions.assertThrows(InvalidUseOfMatchersException.class, () -> Mockito.doReturn(BigInteger.TEN.intValue()));
    }

    @Test
    void testStubbingReturnsProvidedValueAlternativeSyntax() {
        // Alternative Syntax
        Mockito.doReturn(INVALID_STRING_RETURN_VALUE).when(mockService).convertToString(BigInteger.TEN.intValue());
        Assertions.assertEquals(INVALID_STRING_RETURN_VALUE, mockService.convertToString(BigInteger.TEN.intValue()));

        Mockito.doReturn(INVALID_INT_RETURN_VALUE).when(mockService).addTen(BigInteger.TEN.intValue());
        Assertions.assertEquals(INVALID_INT_RETURN_VALUE, mockService.addTen(BigInteger.TEN.intValue()));
    }

    @Test
    void testMultipleStubbingReturnLastValue() {
        Mockito.when(mockService.convertToString(BigInteger.TEN.intValue())).thenReturn(INVALID_STRING_RETURN_VALUE);
        Assertions.assertEquals(INVALID_STRING_RETURN_VALUE, mockService.convertToString(BigInteger.TEN.intValue()));

        Mockito.when(mockService.convertToString(BigInteger.TEN.intValue())).thenReturn("Hello!");
        Assertions.assertEquals("Hello!", mockService.convertToString(BigInteger.TEN.intValue()));
    }

    @Test
    void testStubbingWithException() throws IOException {
        Assertions.assertEquals(0, mockService.throwException(true));

        Mockito.when(mockService.throwException(true)).thenThrow(UnsupportedOperationException.class);
        Assertions.assertThrows(UnsupportedOperationException.class, () -> mockService.throwException(true));

        // void method exception stubbing
        Mockito.doThrow(UnsupportedOperationException.class).when(mockService).doNothing();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> mockService.doNothing());
    }
}
