package me.ronygomes.reference.nest_access;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class SolarSystemTest {

    private static final Class<?>[] SOLAR_SYSTEM_NEST_MEMBERS = {
            SolarSystem.class,
            SolarSystem.Earth.class, SolarSystem.Uranus.class, SolarSystem.Saturn.class,
            SolarSystem.Earth.Asia.class, SolarSystem.Earth.Europe.class
    };

    @Test
    void testNestHost() {
        assertSame(SolarSystem.class.getNestHost(), SolarSystem.class);
        assertSame(SolarSystem.Earth.class.getNestHost(), SolarSystem.class);
        assertSame(SolarSystem.Earth.Asia.class.getNestHost(), SolarSystem.class);
        assertSame(SolarSystem.Earth.Europe.class.getNestHost(), SolarSystem.class);
        assertSame(SolarSystem.Uranus.class.getNestHost(), SolarSystem.class);
        assertSame(SolarSystem.Saturn.class.getNestHost(), SolarSystem.class);
    }

    @Test
    void testNestMember() {
        assertEquals(6, SolarSystem.class.getNestMembers().length);

        List<Class<?>> nestMembers = List.of(SolarSystem.class.getNestMembers());
        assertTrue(nestMembers.containsAll(asList(SOLAR_SYSTEM_NEST_MEMBERS)));
    }

    @Test
    void testNestMate() {
        assertTrue(SolarSystem.Earth.Asia.class.isNestmateOf(SolarSystem.Uranus.class));
        assertTrue(SolarSystem.Earth.Europe.class.isNestmateOf(SolarSystem.class));
        assertTrue(SolarSystem.Earth.Europe.class.isNestmateOf(SolarSystem.Earth.class));
        assertTrue(SolarSystem.Earth.Europe.class.isNestmateOf(SolarSystem.Earth.Asia.class));
        assertTrue(SolarSystem.Uranus.class.isNestmateOf(SolarSystem.Saturn.class));
    }

}
