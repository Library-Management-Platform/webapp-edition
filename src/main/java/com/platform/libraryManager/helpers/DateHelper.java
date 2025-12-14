package com.platform.libraryManager.helpers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public abstract class DateHelper {

    public static LocalDateTime getCurrentLocalDateTime() { return LocalDateTime.now(); }
    public static LocalDateTime getLocalDateTimePlusHours(long hours) { return LocalDateTime.now().plusHours(hours); }

    public static Date getCurrentDate() { return new Date(); }
    public static Date from(Instant instant) { return Date.from(instant); }

    public static Instant getCurrentInstant() {
        return DateHelper.getCurrentLocalDateTime().atZone(ZoneId.systemDefault()).toInstant();
    }


    public static Instant plusSeconds(Instant instant, long seconds) {
        return instant.plusSeconds(seconds);
    }


}
