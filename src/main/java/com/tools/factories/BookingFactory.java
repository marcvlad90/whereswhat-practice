package com.tools.factories;

import com.tools.constants.DateConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.entities.Category;
import com.tools.entities.Item;
import com.tools.entities.User;
import com.tools.utils.DateFormatter;
import com.tools.utils.DateUtils;
import com.tools.utils.SerenitySessionUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BookingFactory {

    public static Booking getDefaultLengthBooking() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        User userRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
        Booking bookingRequest = new Booking();
        bookingRequest.setItemId(item.getId());
        bookingRequest.setUserId(userRequest.getId());
        bookingRequest.setItem(item);
        bookingRequest.setUser(userRequest);
        bookingRequest.setClientTime(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_PATTERN));
        LocalDateTime startDate = DateUtils.addDaysHoursAndMinutesToDate(DateUtils.getCurrentDate(), 0, 0,
                0);
        startDate = DateUtils.roundLocalDateTimeToNearestMinutes(startDate, 5);
        LocalDateTime endDate = DateUtils.addDaysHoursAndMinutesToDate(startDate, 0, category.getDefaultBookingLength(), 0);
        endDate = DateUtils.roundLocalDateTimeToNearestMinutes(endDate, 5);
        bookingRequest.setStartDate(DateFormatter.formatDate(startDate, DateConstants.WW_PATTERN));
        bookingRequest.setEndDate(DateFormatter.formatDate(endDate, DateConstants.WW_PATTERN));
        bookingRequest.setBookingFullDaysNumber();
        System.out.println("client date is: " + bookingRequest.getClientTime());
        System.out.println("start date is: " + bookingRequest.getStartDate());
        System.out.println("end date is: " + bookingRequest.getEndDate());
        return bookingRequest;
    }

    public static Booking getApiWholeDaysBookingInstance(int howManyDaysFromNow, int forHowManyDays) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        User userRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
        Booking bookingRequest = new Booking();
        bookingRequest.setItemId(item.getId());
        bookingRequest.setUserId(userRequest.getId());
        bookingRequest.setItem(item);
        bookingRequest.setUser(userRequest);
        bookingRequest.setClientTime(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_PATTERN));
        LocalDateTime startDate = DateUtils.addDaysToDate(howManyDaysFromNow, DateUtils.getCurrentDate());
        startDate = startDate.truncatedTo(ChronoUnit.DAYS);
        startDate = startDate.toLocalDate().atStartOfDay();
        LocalDateTime endDate = DateUtils.addDaysToDate(forHowManyDays, startDate);
        bookingRequest.setStartDate(DateFormatter.formatDate(startDate, DateConstants.WW_PATTERN));
        bookingRequest.setEndDate(DateFormatter.formatDate(endDate, DateConstants.WW_PATTERN));
        bookingRequest.setBookingFullDaysNumber();
        System.out.println("client date is: " + bookingRequest.getClientTime());
        System.out.println("start date is: " + bookingRequest.getStartDate());
        System.out.println("end date is: " + bookingRequest.getEndDate());
        return bookingRequest;
    }

    public static Booking getApiParameterizedBookingInstance(int howManyDaysFromNow, int howManyHoursFromNow, int howManyMinutesFromNow, int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        User userRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Booking bookingRequest = new Booking();
        bookingRequest.setItemId(item.getId());
        bookingRequest.setUserId(userRequest.getId());
        bookingRequest.setItem(item);
        bookingRequest.setUser(userRequest);
        bookingRequest.setClientTime(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_PATTERN));
        LocalDateTime startDate = DateUtils.roundLocalDateTimeToNearestMinutes(
                DateUtils.addDaysHoursAndMinutesToDate(DateUtils.getCurrentDate(), howManyDaysFromNow, howManyHoursFromNow,
                        howManyMinutesFromNow), 5);
        bookingRequest.setStartDate(DateFormatter.formatDate(startDate, DateConstants.WW_PATTERN));
        LocalDateTime endDate = DateUtils.roundLocalDateTimeToNearestMinutes(
                DateUtils.addDaysHoursAndMinutesToDate(startDate, forHowManyDays, forHowManyHours, forHowManyMinutes), 5);
        bookingRequest.setEndDate(DateFormatter.formatDate(endDate, DateConstants.WW_PATTERN));
        bookingRequest.setBookingFullDaysNumber();
        System.out.println("client date is: " + bookingRequest.getClientTime());
        System.out.println("start date is: " + bookingRequest.getStartDate());
        System.out.println("end date is: " + bookingRequest.getEndDate());
        return bookingRequest;
    }

    public static Booking getApiBookingFromNowInstance(int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        User userRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Booking bookingRequest = new Booking();
        bookingRequest.setItemId(item.getId());
        bookingRequest.setUserId(userRequest.getId());
        bookingRequest.setItem(item);
        bookingRequest.setUser(userRequest);
        bookingRequest.setClientTime(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_PATTERN));
        LocalDateTime startDate = DateUtils.getCurrentDate();
        bookingRequest.setStartDate(DateFormatter.formatDate(startDate, DateConstants.WW_PATTERN));
        LocalDateTime endDate = DateUtils.roundLocalDateTimeToNearestMinutes(
                DateUtils.addDaysHoursAndMinutesToDate(startDate, forHowManyDays, forHowManyHours, forHowManyMinutes), 5);
        bookingRequest.setEndDate(DateFormatter.formatDate(endDate, DateConstants.WW_PATTERN));
        bookingRequest.setBookingFullDaysNumber();
        System.out.println("client date is: " + bookingRequest.getClientTime());
        System.out.println("start date is: " + bookingRequest.getStartDate());
        System.out.println("end date is: " + bookingRequest.getEndDate());
        return bookingRequest;
    }
}
