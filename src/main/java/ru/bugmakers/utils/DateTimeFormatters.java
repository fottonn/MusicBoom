package ru.bugmakers.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.temporal.ChronoField.*;

/**
 * Created by Ivan
 */
public class DateTimeFormatters {

    /**
     * dd.MM.yyyy
     */
    public static final DateTimeFormatter DATE_FORMATTER;

    static {
        DATE_FORMATTER = new DateTimeFormatterBuilder()
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral('.')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('.')
                .appendValue(YEAR, 4)
                .toFormatter();
    }

    /**
     * HH:mm:ss
     */
    public static final DateTimeFormatter TIME_FORMATTER;

    static {
        TIME_FORMATTER = new DateTimeFormatterBuilder()
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter();
    }

    /**
     * dd.MM.yyyy'T'HH:mm:ss
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER;

    static {
        DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .append(DATE_FORMATTER)
                .appendLiteral("'T'")
                .append(TIME_FORMATTER)
                .toFormatter();
    }

    /**
     * dd.MM.yyyy'T'HH:mm:ss+03:00
     */
    public static final DateTimeFormatter ZONED_DATE_TIME_FORMATTER;

    static {
        ZONED_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .append(DATE_TIME_FORMATTER)
                .appendOffsetId()
                .toFormatter();
    }

    public static LocalDateTime parseLocalDateTime(String date, String time) {
        return LocalDateTime.parse(date + "'T'" + time, DATE_TIME_FORMATTER);
    }

}
