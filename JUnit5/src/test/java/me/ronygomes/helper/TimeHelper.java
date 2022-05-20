package me.ronygomes.helper;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeHelper {

    private Clock clock;

    public TimeHelper() {
        this(Clock.systemDefaultZone());
    }

    public TimeHelper(Clock clock) {
        this.clock = clock;
    }

    public boolean isFutureDateTime(Date date) {

        LocalDateTime targetDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return targetDate.isAfter(LocalDateTime.now(clock));
    }
}
