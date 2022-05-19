package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigInteger;

public class SpyStubbingTest {

    private static final String INVALID_STRING_RETURN_VALUE = "NaN";
    private static final int INVALID_INT_RETURN_VALUE = Integer.MAX_VALUE;
    private CoreService mockService;

    @BeforeEach
    void setup() {
        this.mockService = Mockito.spy(CoreService.class);
    }

    @Test
    void testWithoutStubbingReturnsMethodInvokedValue() {
        Assertions.assertEquals("10", mockService.convertToString(BigInteger.TEN.intValue()));
        Assertions.assertEquals(20, mockService.addTen(BigInteger.TEN.intValue()));
    }

    @Test
    void testStubbingReturnsProvidedValue() {
        Mockito.when(mockService.convertToString(BigInteger.TEN.intValue())).thenReturn(INVALID_STRING_RETURN_VALUE);
        Assertions.assertEquals(INVALID_STRING_RETURN_VALUE, mockService.convertToString(BigInteger.TEN.intValue()));

        Mockito.when(mockService.addTen(BigInteger.TEN.intValue())).thenReturn(INVALID_INT_RETURN_VALUE);
        Assertions.assertEquals(INVALID_INT_RETURN_VALUE, mockService.addTen(BigInteger.TEN.intValue()));
    }
}
