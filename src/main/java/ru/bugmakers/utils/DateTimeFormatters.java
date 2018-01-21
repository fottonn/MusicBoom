package ru.bugmakers.utils;

import java.time.format.DateTimeFormatter;

/**
 * Created by Ivan
 */
public interface DateTimeFormatters {

    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy'T'HH:mm:ss");


}
