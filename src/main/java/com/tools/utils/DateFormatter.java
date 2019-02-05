package com.tools.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {

    public static String formatStringDate(String dateString, String formatString) {
        LocalDateTime date = LocalDateTime.parse(dateString);

        return date.format(DateTimeFormatter.ofPattern(formatString));
    }

    public static String formatDate(LocalDateTime date, String format) {

        return date.format(DateTimeFormatter.ofPattern(format));
    }
}
