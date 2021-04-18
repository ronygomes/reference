package me.ronygomes.junit5demo;

import me.ronygomes.domain.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * For custom message takes following placeholder
 * {displayName}, {index}, {arguments}
 */
public class ParameterizedTests {

    enum Color {
        RED, GREEN, BLUE, RED_1,
    }

    /**
     * BeforeEach/AfterEach will be called for each test
     * Following example will call BeforeEach 5 times
     * <p>
     * Default Display Name: [{index}] {arguments};
     */
    @ParameterizedTest(name = "{index} -> ({arguments})")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    // Can be only used with 1 parameter
    void testParameterizedTestWithValueSource(int i) {
        Assertions.assertTrue(i > 0);
    }

    /**
     * EnumSource have names() and modes(), which can be used to filter enum values
     */
    @ParameterizedTest
    @EnumSource(Color.class)
    // Can be only used with 1 parameter
    void testParameterizedTestWithEnumSource(Color c) {
        Assertions.assertNotEquals("CYAN", c.name());
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testParameterizedTestWithMethodSource(String value) {
        Assertions.assertNotEquals("Four", value);
    }

    private static List<String> stringProvider() {
        return Arrays.asList("One", "Two", "Three");
    }

    @ParameterizedTest
    @MethodSource("stringProvider2")
    void testParameterizedTestWithArgumentsMethodSource(String value, int len) {
        Assertions.assertEquals(len, value.length());
    }

    private static List<Arguments> stringProvider2() {
        return stringProvider()
                .stream()
                .map(v -> Arguments.of(v, v.length()))
                .collect(Collectors.toList());
    }

    @ParameterizedTest
    @CsvSource(value = {"3, 5, 8", "1, 9, 10"})
    void testParameterizedTestWithCsvSource(int num1, int num2, int sum) {
        Assertions.assertEquals(sum, num1 + num2);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv_data.csv")
    void testParameterizedTestWithCsvFileSource(int num, String numName, int otherNum, int sum) {
        Assertions.assertNotEquals("Three", numName);
        Assertions.assertEquals(sum, num + otherNum);
    }

    @ParameterizedTest
    @ArgumentsSource(CustomArgumentProvider.class)
    void testParameterizedTestWithArgumentSource(String value, int len) {
        Assertions.assertEquals(len, value.length());
    }

    @ParameterizedTest
    @CsvSource(value = "1; John; 25")
    void testParameterizedTestWithArgumentSourceAndArgument(@ConvertWith(PersonArgumentConverter.class) Person p) {
        Assertions.assertEquals(1, p.getId());
        Assertions.assertEquals("John", p.getName());
        Assertions.assertEquals(25, p.getAge());
    }

    static class CustomArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of("One", "Two", "Three")
                    .map(v -> Arguments.of(v, v.length()));
        }
    }

    // Has an implementation of ArgumentConverter named SimpleArgumentConverter
    static class PersonArgumentConverter implements ArgumentConverter {

        @Override
        public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
            Assertions.assertEquals(String.class, source.getClass());
            String[] parts = ((String) source).split(";");

            Person p = new Person();
            p.setId(Long.parseLong(parts[0].trim()));
            p.setName(parts[1].trim());
            p.setAge(Integer.parseInt(parts[2].trim()));

            return p;
        }
    }
}
