package me.ronygomes.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeHelperTest {

    private static final Instant FIXED_TIME = LocalDate.of(2020, 1, 4)
            .atStartOfDay(ZoneId.systemDefault()).toInstant();

    private static final Clock CLOCK = Clock.fixed(FIXED_TIME, ZoneId.systemDefault());

    private TimeHelper timeHelper;

    @BeforeEach
    void setup() {
        this.timeHelper = new TimeHelper(CLOCK);
    }

    @Test
    void testFutureDateTime() {
        LocalDateTime sameTime = LocalDateTime.parse("2020-01-04T00:00:00");
        Assertions.assertFalse(timeHelper.isFutureDateTime(toDate(sameTime)));

        LocalDateTime pastTime = LocalDateTime.parse("2020-01-03T23:59:59");
        Assertions.assertFalse(timeHelper.isFutureDateTime(toDate(pastTime)));

        LocalDateTime futureTime = LocalDateTime.parse("2020-01-04T00:00:01");
        Assertions.assertTrue(timeHelper.isFutureDateTime(toDate(futureTime)));
    }

    private Date toDate(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        GregorianCalendar cal = GregorianCalendar.from(zonedDateTime);
        return cal.getTime();
    }
}
