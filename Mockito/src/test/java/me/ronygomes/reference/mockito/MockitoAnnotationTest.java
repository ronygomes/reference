package me.ronygomes.reference.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

/**
 * Initializes objects annotated with Mockito annotations for given testClass: @Mock, @Spy, @Captor, @InjectMocks
 */
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class MockitoAnnotationTest {

    @Mock
    private CoreService mockCoreService;

    @Mock(lenient = true)
    private CoreService mockCoreService2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMockInitialization1() {
        Assertions.assertNull(mockCoreService.convertToString(10));
    }

    @Test
    void testMockInitialization2() {
        Mockito.when(mockCoreService.convertToString(Mockito.anyInt())).thenReturn("10");
        Assertions.assertEquals("10", mockCoreService.convertToString(10));
    }

    @Test
    void testMockLenient() {
        Mockito.when(mockCoreService2.convertToString(10)).thenReturn("10");

        // Following line will throw UnnecessaryStubbingException when @Mock(lenient = false)
        // Here stubbing is defined but not used. Only convertToString(10) is used

        Mockito.when(mockCoreService2.convertToString(20)).thenReturn("20");

        // Another option is Mockito.lenient()
        // Following like will make this stubbing lenient even if @Mock(lenient = false)
        Mockito.lenient().when(mockCoreService2.convertToString(30)).thenReturn("30");

        Assertions.assertEquals("10", mockCoreService2.convertToString(10));
    }
}
