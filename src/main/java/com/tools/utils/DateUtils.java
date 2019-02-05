package com.tools.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDateTime addDaysToDate(int days, LocalDateTime date) {
        return date.plusDays(days);
    }

    public static LocalDateTime addMinutesToDate(int minutes, LocalDateTime date) {
        return date.plusMinutes(minutes);
    }

    public static LocalDateTime getCurrentDate() {
        return LocalDateTime.now();
    }

    public static LocalDateTime addDaysHoursAndMinutesToDate(LocalDateTime date, int days, int hours, int minutes) {
        date = DateUtils.addMinutesToDate(minutes, addHoursToDate(hours, addDaysToDate(days, date)));
        return date;
    }

    public static LocalDateTime addHoursToDate(int hours, LocalDateTime date) {
        return date.plusMinutes(hours * 60);
    }

    public static LocalDateTime parseStringIntoDate(String date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }

    public static LocalDateTime roundLocalDateTimeToNearestMinutes(LocalDateTime localDateTime, int minutesDividedValue) {
        int minute = localDateTime.getMinute();
        for (int i = 0; i < minutesDividedValue; i++) {
            if ((minute % minutesDividedValue) != 0) {
                minute++;
                localDateTime = localDateTime.plusMinutes(1);
            } else {
                break;
            }
        }
        return localDateTime;
    }

    public static String getDayFromDate(String date) {
        String[] dateParts = date.split(" ");
        return dateParts[1].trim();
    }

    public static String getMonthFromDate(String date) {
        String[] dateParts = date.split(" ");
        return dateParts[0].trim();
    }

    public static String getYearFromDate(String date) {
        String[] dateParts = date.split(" ");
        return dateParts[2].trim().replace(",", "");
    }

    public static String getHourFromDate(String date) {
        String[] dateParts = date.split(", ");
        return dateParts[1].trim();
    }
}
