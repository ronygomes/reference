package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Initializes objects annotated with Mockito annotations for given testClass: @Mock, @Spy, @Captor, @InjectMocks
 */
public class MockitoAnnotationTest {

    @Mock
    private CoreService mockcoreService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMockInitialization1() {
        Assertions.assertNull(mockcoreService.convertToString(10));
    }

    @Test
    void testMockInitialization2() {
        Mockito.when(mockcoreService.convertToString(Mockito.anyInt())).thenReturn("10");
        Assertions.assertEquals("10", mockcoreService.convertToString(10));
    }
}
