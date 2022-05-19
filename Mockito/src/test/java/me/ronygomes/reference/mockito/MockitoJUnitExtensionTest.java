package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Initializes objects annotated with Mockito annotations for given testClass: @Mock, @Spy, @Captor, @InjectMocks
 */
// Requires org.mockito:mockito-junit-jupiter:3.7.7
@ExtendWith(MockitoExtension.class)
public class MockitoJUnitExtensionTest {

    @Mock
    private CoreService mockService;

    @Test
    void testMockInitialization1() {
        Assertions.assertNull(mockService.convertToString(10));
    }

    @Test
    void testMockInitialization2() {
        Mockito.when(mockService.convertToString(Mockito.anyInt())).thenReturn("10");
        Assertions.assertEquals("10", mockService.convertToString(10));
    }
}
