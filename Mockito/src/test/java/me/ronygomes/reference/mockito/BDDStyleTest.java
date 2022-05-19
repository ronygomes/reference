package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.util.List;

/**
 * Mockito supports BDD style alias for when(..).thenReturn() syntax
 * Behaviour is identical, only syntax is different
 */
public class BDDStyleTest {

    private static final String INVALID_STRING_RETURN_VALUE = "NaN";
    private static final int INVALID_INT_RETURN_VALUE = Integer.MAX_VALUE;
    private CoreService mockService;

    @BeforeEach
    void setup() {
        this.mockService = Mockito.mock(CoreService.class);
    }

    @Test
    void testStubbingReturnsProvidedValue() {
        BDDMockito.given(mockService.convertToString(BigInteger.TEN.intValue())).willReturn(INVALID_STRING_RETURN_VALUE);
        Assertions.assertEquals(INVALID_STRING_RETURN_VALUE, mockService.convertToString(BigInteger.TEN.intValue()));

        BDDMockito.given(mockService.addTen(BigInteger.TEN.intValue())).willReturn(INVALID_INT_RETURN_VALUE);
        Assertions.assertEquals(INVALID_INT_RETURN_VALUE, mockService.addTen(BigInteger.TEN.intValue()));
    }

    @Test
    void testMultipleSubbingReturnLastValue() {
        BDDMockito.given(mockService.convertToString(BigInteger.TEN.intValue())).willReturn(INVALID_STRING_RETURN_VALUE);
        Assertions.assertEquals(INVALID_STRING_RETURN_VALUE, mockService.convertToString(BigInteger.TEN.intValue()));

        BDDMockito.given(mockService.convertToString(BigInteger.TEN.intValue())).willReturn("Hello!");
        Assertions.assertEquals("Hello!", mockService.convertToString(BigInteger.TEN.intValue()));
    }

    @Test
    void testVerify() {
        List<String> fruits = Mockito.mock(List.class);
        fruits.add("Apple");

        BDDMockito.then(fruits).should().add("Apple");
        BDDMockito.then(fruits).should(Mockito.never()).clear();
        BDDMockito.then((fruits)).shouldHaveNoMoreInteractions();
    }
}
