package me.ronygomes.reference;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.RecordComponent;

import static org.junit.jupiter.api.Assertions.*;

public class RecordTest {

    private static String PERSON_NAME = "John";
    private static String PERSON_EMAIL = "john@example.com";
    private static int PERSON_AGE = 25;

    private static String DEFAULT_TO_STRING = "Person[name=John, email=john@example.com, age=25]";

    @Test
    void testParameters() {
        Person p = new Person(PERSON_NAME, PERSON_EMAIL, PERSON_AGE);

        assertEquals(PERSON_NAME, p.name());
        assertEquals(PERSON_EMAIL, p.email());
        assertEquals(PERSON_AGE, p.age());
    }

    @Test
    void testEquality() {
        Person p1 = new Person(PERSON_NAME, PERSON_EMAIL, PERSON_AGE);
        Person p2 = new Person(PERSON_NAME, PERSON_EMAIL, PERSON_AGE);

        assertNotSame(p1, p2);
        assertEquals(p1, p2);
        assertThrows(IllegalArgumentException.class, () -> new Person(PERSON_NAME, PERSON_EMAIL, -1));
    }

    @Test
    void testDefaultToString() {
        Person p = new Person(PERSON_NAME, PERSON_EMAIL, PERSON_AGE);
        assertEquals(DEFAULT_TO_STRING, p.toString());
    }

    @Test
    void testReflectionApi() {
        Class<Person> clazz = Person.class;

        assertTrue(clazz.isRecord());
        assertTrue(Modifier.isFinal(clazz.getModifiers()));
        assertSame(java.lang.Record.class, clazz.getSuperclass());

        RecordComponent[] components = clazz.getRecordComponents();
        assertEquals(3, components.length);

        assertEquals("name", components[0].getName());
        assertSame(java.lang.String.class, components[0].getType());

        assertEquals("email", components[1].getName());
        assertSame(java.lang.String.class, components[1].getType());

        assertEquals("age", components[2].getName());
        assertSame(int.class, components[2].getType());
    }

    @Test
    void testInterface() {
        Validator p1 = new Person(PERSON_NAME, PERSON_EMAIL, PERSON_AGE);
        assertTrue(p1.isValid());

        Validator p2 = new Person(PERSON_NAME, null, PERSON_AGE);
        assertFalse(p2.isValid());
    }

    @Test
    void testLocalRecord() {
        Person p1 = new Person(PERSON_NAME, PERSON_EMAIL, PERSON_AGE);
        assertTrue(p1.isEmailPrefixAndNameSame());

        Person p2 = new Person("Jane", PERSON_EMAIL, PERSON_AGE);
        assertFalse(p2.isEmailPrefixAndNameSame());
    }

    @Test
    void testFieldsUsingFields() throws NoSuchFieldException {
        Person p = new Person(PERSON_NAME, PERSON_EMAIL, PERSON_AGE);
        Class<Person> clazz = Person.class;

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);

        assertTrue(Modifier.isFinal(name.getModifiers()));
        assertThrows(IllegalAccessException.class, () -> name.set(p, "Jane"));

        Field email = clazz.getDeclaredField("email");
        email.setAccessible(true);
        assertThrows(IllegalAccessException.class, () -> email.set(p, "jane@example.com"));

        assertTrue(Modifier.isFinal(email.getModifiers()));

        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);

        assertTrue(Modifier.isFinal(age.getModifiers()));
        assertThrows(IllegalAccessException.class, () -> age.set(p, 23));
    }

}
