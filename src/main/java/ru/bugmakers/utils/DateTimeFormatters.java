package ru.bugmakers.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Created by Ivan
 */
public class DateTimeFormatters {

    /**
     * yyyy-MM-dd
     */
    public static final DateTimeFormatter DATE_FORMATTER;

    static {
        DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    /**
     * HH:mm:ss
     */
    public static final DateTimeFormatter TIME_FORMATTER;

    static {
        TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
    }

    /**
     * yyyy-MM-ddTHH:mm:ss
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER;

    static {
        DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    }

    /**
     * yyyy-MM-ddTHH:mm:ss+HH:MM
     */
    public static final DateTimeFormatter ZONED_DATE_TIME_FORMATTER;

    static {
        ZONED_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
                .append(DATE_TIME_FORMATTER)
                .appendOffset("+HH:MM", "+00:00")
                .toFormatter();
    }

    public static LocalDateTime parseLocalDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

}
