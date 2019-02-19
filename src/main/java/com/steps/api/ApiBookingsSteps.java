package com.steps.api;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.DateConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.entities.BookingsCollection;
import com.tools.entities.Category;
import com.tools.entities.Item;
import com.tools.factories.BookingFactory;
import com.tools.utils.DateFormatter;
import com.tools.utils.DateUtils;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ApiBookingsSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void bookItem(int howManyDaysFromNow, int howManyHoursFromNow, int howManyMinutesFromNow, int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        Booking bookingRequest = BookingFactory.getApiParameterizedBookingInstance(howManyDaysFromNow, howManyHoursFromNow, howManyMinutesFromNow,
                forHowManyDays, forHowManyHours, forHowManyMinutes);
        Booking bookingResponse = createResource(ApiUrlConstants.BOOKINGS, bookingRequest, Booking.class);
        bookingRequest = (Booking)InstanceUtils.mergeObjects(bookingRequest, bookingResponse);
        bookingRequest.setEndDateInitial(bookingRequest.getEndDate());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
    }

    @Step
    public void bookItemWithDefaultSetLength() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        Booking bookingRequest = BookingFactory.getApiParameterizedBookingInstance(0, 0, 0, 0, category.getDefaultBookingLength(), 0);
        Booking bookingResponse = createResource(ApiUrlConstants.BOOKINGS, bookingRequest, Booking.class);
        bookingRequest = (Booking)InstanceUtils.mergeObjects(bookingRequest, bookingResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
    }

    @Step
    public void bookItemForWholeDays(int howManyDaysFromNow, int forHowManyDays) {
        Booking bookingRequest = BookingFactory.getApiWholeDaysBookingInstance(howManyDaysFromNow, forHowManyDays);
        Booking bookingResponse = createResource(ApiUrlConstants.BOOKINGS, bookingRequest, Booking.class);
        bookingRequest = (Booking)InstanceUtils.mergeObjects(bookingRequest, bookingResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
    }

    @Step
    public void bookItemWithFailure(int howManyDaysFromNow, int howManyHoursFromNow, int howManyMinutesFromNow, int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        Booking bookingRequest = BookingFactory.getApiParameterizedBookingInstance(howManyDaysFromNow, howManyHoursFromNow, howManyMinutesFromNow,
                forHowManyDays, forHowManyHours, forHowManyMinutes);
        Booking bookingResponse = createResourceWithFailure(ApiUrlConstants.BOOKINGS, bookingRequest, Booking.class);
        bookingRequest = (Booking)InstanceUtils.mergeObjects(bookingRequest, bookingResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
    }

    @Step
    public void bookTheSameItemInTheSamePeriod() {
        Booking bookingRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        createResource(ApiUrlConstants.BOOKINGS, bookingRequest, Booking.class);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
    }

    @Step
    public void deleteItemBooking() {
        Booking bookingRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        deleteResource(ApiUrlConstants.BOOKINGS, bookingRequest.getId());
    }

    @SuppressWarnings("null")
    @Step
    public void checkIfBookingOfItemExists(Boolean shouldExist) {
        Booking booking = getBooking();
        if (shouldExist) {
            Assert.assertTrue(String.format("Booking of item %s does not exist or its details are incorrect!", booking.getItem().getTitle()), booking != null);
        } else {
            try {
                Assert.assertTrue(String.format("Booking of item %s exists and it should not!", booking.getItem().getTitle()),
                        booking == null);
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    @Step
    public void checkIfBookingsOfItemsExist(Boolean shouldExist) {
        List<Booking> bookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
        for (int i = 0; i < bookings.size(); i++) {
            if (shouldExist) {
                Assert.assertTrue(String.format("Booking of item %s does not exist or its details are incorrect!", bookings.get(i).getItem().getTitle()),
                        getBooking(bookings.get(i)) != null);
            } else {
                try {
                    Assert.assertTrue(String.format("Booking of item %s exists and it should not!", bookings.get(i).getItem().getTitle()),
                            getBooking(bookings.get(i)) == null);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }
    }

    @Step
    public void checkThatItemsAreBooked() {
        Booking booking = getBooking();
        Assert.assertTrue(String.format("Booking of item %s does not exist or its details are incorrect!", booking.getItem().getTitle()), booking != null);
    }

    @Step
    public Booking getBooking(Booking booking) {
        BookingsCollection[] bookings = getResource(ApiUrlConstants.BOOKINGS + "?perPage=9999", BookingsCollection[].class);
        for (int i = 0; i < bookings.length; i++) {
            if ((bookings[i].getId() == booking.getId()) && (bookings[i].getItem().getId() == booking.getItem().getId())) {
                bookings[i].setStartDate(DateFormatter.formatDate(
                        DateUtils.parseStringIntoDate(bookings[i].getStartDate(), DateConstants.WW_RETURN_DATE_PATTERN), DateConstants.WW_PATTERN));
                bookings[i].setEndDate(DateFormatter.formatDate(
                        DateUtils.parseStringIntoDate(bookings[i].getEndDate(), DateConstants.WW_RETURN_DATE_PATTERN), DateConstants.WW_PATTERN));
                if (bookings[i].getReturnDate() != null) {
                    bookings[i].setEndDate(DateFormatter.formatStringDate(bookings[i].getReturnDate(), DateConstants.WW_PATTERN));
                }
                if ((bookings[i].getItemId() == booking.getItemId()) && (bookings[i].getStartDate().equals(
                        booking.getStartDate()) && (bookings[i].getEndDate().equals(booking.getEndDate())))) {
                    return bookings[i];
                }
            }
        }
        return null;
    }

    @Step
    public Booking getBooking() {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        return getBooking(booking);
    }

    @Step
    public void checkThatBookedItemWasReturned() {
        Assert.assertTrue("The booking was not returned!", getBooking().isReturnItem());
    }

    @Step
    public void returnBookedItem(Booking bookingReturnRequest) {
        if (!bookingReturnRequest.isReturnItem() && bookingReturnRequest.isCanReturn()) {
            bookingReturnRequest.setReturnItem(true);
            bookingReturnRequest.setStatus("Completed");
            if (DateUtils.getCurrentDate().getSecond() > 55) {
                waitABit(5000);
            }
            bookingReturnRequest.setEndDate(DateFormatter.formatDate(
                    DateUtils.getCurrentDate().plusHours(1), DateConstants.WW_PATTERN));
            Booking bookingReturnResponse = updateResource(ApiUrlConstants.UPDATED_BOOKING, bookingReturnRequest, Booking.class,
                    bookingReturnRequest.getId());
            try {
                InstanceUtils.mergeObjects(bookingReturnRequest, bookingReturnResponse);
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
            SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingReturnRequest);
            SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingReturnRequest);
        }
    }

    @Step
    public void returnBookedItem() {
        Booking bookingReturnRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        returnBookedItem(bookingReturnRequest);
    }

    @Step
    public void acceptItemBooking() {
        Booking bookingRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        bookingRequest.setStatus("accepted");
        Booking bookingResponse = updateResource(ApiUrlConstants.UPDATED_BOOKING, bookingRequest, Booking.class, bookingRequest.getId());
        bookingRequest.setEndDate(DateFormatter.formatStringDate(bookingResponse.getEndDate(), DateConstants.WW_PATTERN));
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
    }

    @Step
    public void declineItemBooking() {
        Booking bookingRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        bookingRequest.setStatus("declined");
        updateResource(ApiUrlConstants.UPDATED_BOOKING, bookingRequest, bookingRequest.getId());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
    }

    @Step
    public void declineItemBookingExtension() {
        Booking bookingRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        bookingRequest.setStatus("accepted");
        Booking bookingReponse = updateResource(ApiUrlConstants.UPDATED_BOOKING, bookingRequest, Booking.class, bookingRequest.getId());
        bookingRequest.setEndDate(DateFormatter.formatStringDate(bookingReponse.getEndDate(), DateConstants.WW_PATTERN));
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
    }

    @Step
    public void returnAllBookedItemsFromSession() {
        List<Item> items = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
        try {
            BookingsCollection[] bookings = getResource(ApiUrlConstants.BOOKINGS + "?perPage=9999", BookingsCollection[].class);
            for (int i = 0; i < bookings.length; i++) {
                for (Item item : items) {
                    if (bookings[i].getItem().getId() == item.getId()) {
                        returnBookedItem(bookings[i]);
                        break;
                    }
                }
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    @Step
    public void returnAllBookedItems() {
        try {
            BookingsCollection[] bookings = getResource(ApiUrlConstants.BOOKINGS + "?perPage=9999", BookingsCollection[].class);
            for (int i = 0; i < bookings.length; i++) {
                returnBookedItem(bookings[i]);
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    @Step
    public void checkThatAllBookedItemsWereReturned() {
        List<Booking> bookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
        for (Booking booking : bookings) {
            Assert.assertTrue(String.format("The booking of item %s was not returned!", booking.getItem().getTitle()), booking.isReturnItem());
        }
    }

    @Step
    public void extendBooking(int numberOfDaysToExtend, int numberOfHoursToExtend) {
        Booking bookingExtendRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        bookingExtendRequest.setEndDatePending(DateFormatter.formatDate(
                DateUtils.addHoursToDate(numberOfHoursToExtend,
                        DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDate(), DateConstants.WW_PATTERN)),
                DateConstants.WW_PATTERN)
                .toString());
        bookingExtendRequest.setEndDatePending(DateFormatter
                .formatDate(
                        DateUtils.addDaysToDate(numberOfDaysToExtend,
                                DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDatePending(), DateConstants.WW_PATTERN)),
                        DateConstants.WW_PATTERN)
                .toString());
        bookingExtendRequest.setStatus(null);
        bookingExtendRequest.setClientTime(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_RETURN_DATE_PATTERN).toString());
        bookingExtendRequest.setEndDatePending(DateFormatter.formatDate(
                DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDatePending(), DateConstants.WW_PATTERN),
                DateConstants.WW_RETURN_DATE_PATTERN).toString());
        LocalDateTime startDate = DateUtils.parseStringIntoDate((bookingExtendRequest.getStartDate()), DateConstants.WW_PATTERN);
        LocalDateTime endDatePending = DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDatePending(),
                DateConstants.WW_RETURN_DATE_PATTERN);
        if (ChronoUnit.DAYS.between(startDate, endDatePending) > 0) {
            bookingExtendRequest.setFullDaysBookingNumber(ChronoUnit.DAYS.between(
                    startDate.plusMinutes(60 - startDate.getMinute()).plusHours(23 - startDate.getHour()),
                    endDatePending.minusMinutes(endDatePending.getMinute()).minusHours(endDatePending.getHour())));
        } else {
            bookingExtendRequest.setFullDaysBookingNumber(0);
        }
        Booking bookingExtendResponse = updateResource(ApiUrlConstants.UPDATED_BOOKING, bookingExtendRequest, Booking.class, bookingExtendRequest.getId());
        bookingExtendRequest = (Booking)InstanceUtils.mergeObjects(bookingExtendRequest, bookingExtendResponse);
        bookingExtendRequest.setEndDate(DateFormatter.formatStringDate(bookingExtendResponse.getEndDate(), DateConstants.WW_PATTERN));
        if (bookingExtendResponse.getExtensionStatus().equals("pending")) {
            bookingExtendRequest.setStatus("pending");
        }
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingExtendRequest);
    }

    @Step
    public void extendBookingWithFailure(int numberOfDaysToExtend, int numberOfHoursToExtend) {
        Booking bookingExtendRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        bookingExtendRequest.setEndDatePending(DateFormatter.formatDate(
                DateUtils.addHoursToDate(numberOfHoursToExtend,
                        DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDate(), DateConstants.WW_PATTERN)), DateConstants.WW_PATTERN).toString());
        bookingExtendRequest.setEndDatePending(DateFormatter
                .formatDate(
                        DateUtils.addDaysToDate(numberOfDaysToExtend,
                                DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDatePending(), DateConstants.WW_PATTERN)), DateConstants.WW_PATTERN)
                .toString());
        bookingExtendRequest.setStatus(null);
        bookingExtendRequest.setClientTime(DateFormatter.formatDate(DateUtils.getCurrentDate(), DateConstants.WW_RETURN_DATE_PATTERN).toString());
        bookingExtendRequest.setEndDatePending(DateFormatter.formatDate(
                DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDatePending(), DateConstants.WW_PATTERN),
                DateConstants.WW_RETURN_DATE_PATTERN).toString());
        Booking bookingExtendResponse = updateResourceWithFailure(ApiUrlConstants.UPDATED_BOOKING, bookingExtendRequest, Booking.class,
                bookingExtendRequest.getId());
        bookingExtendRequest = (Booking)InstanceUtils.mergeObjects(bookingExtendRequest, bookingExtendResponse);
        bookingExtendRequest.setEndDate(bookingExtendRequest.getEndDatePending());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingExtendRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingExtendRequest);
    }
}