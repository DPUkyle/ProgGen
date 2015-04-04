package com.kylemoore;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateUtil {

    private DateUtil() {} //prevent instantiation

    private static final int _defaultYear = 2015;

    private static final DateTimeFormatter _format = new DateTimeFormatterBuilder()
            .appendPattern("EEEE, MMMM d h:mm a")
            .parseDefaulting(ChronoField.YEAR, _defaultYear)
            .toFormatter()
            .withLocale(Locale.US)
            .withZone(ZoneId.of("America/Chicago"));

    public static ZonedDateTime joinDateAndTime(String date, String time) {
        return ZonedDateTime.parse(date + ' ' + time, _format);
    }

    public static ZonedDateTime asEasternTime(ZonedDateTime datetime) {
        return datetime.withZoneSameInstant(ZoneId.of("America/Indiana/Indianapolis"));
    }
}
