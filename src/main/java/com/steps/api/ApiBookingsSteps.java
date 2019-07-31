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
    public void bookItemFromNow(int forHowManyDays, int forHowManyHours, int forHowManyMinutes) {
        if (DateUtils.getCurrentDate().getSecond() > 55) {
            waitABit(5000);
        }
        Booking bookingRequest = BookingFactory.getApiBookingFromNowInstance(forHowManyDays, forHowManyHours, forHowManyDays);
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
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Booking expectedBooking = getBooking(booking, item);
        if (shouldExist) {
            Assert.assertTrue(String.format("Booking of item %s does not exist or its details are incorrect!", expectedBooking.getItem().getTitle()),
                    expectedBooking.getItem().getTitle().contentEquals(item.getTitle()));
        } else {
            try {
                Assert.assertTrue(String.format("Booking of item %s exists and it should not!", expectedBooking.getItem().getTitle()),
                        expectedBooking == null);
            } catch (NullPointerException e) {
                e.getMessage();
            }
        }
    }

    @SuppressWarnings("null")
    @Step
    public void checkIfBookingsOfItemsExist(Boolean shouldExist) {
        List<Booking> bookingsList = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
        List<Item> itemsList = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
        for (Item item : itemsList) {
            for (Booking booking : bookingsList) {
                Booking expectedBooking = getBooking(booking, item);
                if (shouldExist) {
                    Assert.assertTrue(String.format("Booking of item %s does not exist or its details are incorrect!", expectedBooking.getItem().getTitle()),
                            expectedBooking != null);
                    break;
                } else {
                    try {
                        Assert.assertTrue(String.format("Booking of item %s exists and it should not!", expectedBooking.getItem().getTitle()),
                                expectedBooking == null);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                }
            }
        }
    }

    @Step
    public Booking getBooking(Booking booking, Item item) {
        List<Booking> bookingsList = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
        for (Booking bookingItem : bookingsList) {
            try {
                Booking bookingResponse = getResource(ApiUrlConstants.BOOKINGS + "/" + booking.getId(), Booking.class);
                if ((bookingResponse.getId() == bookingItem.getId()) && (bookingResponse.getItem().getId() == item.getId())) {
                    bookingResponse.setStartDate(DateFormatter.formatDate(
                            DateUtils.parseStringIntoDate(bookingResponse.getStartDate(), DateConstants.WW_RETURN_DATE_PATTERN), DateConstants.WW_PATTERN));
                    bookingResponse.setEndDate(DateFormatter.formatDate(
                            DateUtils.parseStringIntoDate(bookingResponse.getEndDate(), DateConstants.WW_RETURN_DATE_PATTERN), DateConstants.WW_PATTERN));
                    if (bookingResponse.getReturnDate() != null) {
                        bookingResponse.setEndDate(DateFormatter.formatStringDate(bookingResponse.getReturnDate(), DateConstants.WW_PATTERN));
                    }
                    if ((bookingResponse.getStartDate().equals(booking.getStartDate()) && (bookingResponse.getEndDate().equals(bookingItem.getEndDate())))) {
                        return bookingResponse;
                    }
                }
            } catch (NullPointerException e) {
                e.getMessage();
            }
        }
        return null;
    }

    @Step
    public void checkThatBookedItemWasReturned() {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Assert.assertTrue("The booking was not returned!", getBooking(booking, item).isReturnItem());
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
                    DateUtils.getCurrentDate(), DateConstants.WW_PATTERN));
            Booking bookingReturnResponse = updateResource(ApiUrlConstants.UPDATED_BOOKING, bookingReturnRequest, Booking.class,
                    bookingReturnRequest.getId());
            try {
                InstanceUtils.mergeObjects(bookingReturnRequest, bookingReturnResponse);
            } catch (IllegalArgumentException e) {
                e.getMessage();
            }
            bookingReturnRequest.setBookingFullDaysNumber();
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
        bookingRequest.setBookingFullDaysNumber();
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
        bookingRequest.setBookingFullDaysNumber();
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
    }

    @Step
    public void returnAllBookedItems() {
        List<Booking> bookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
        try {
            for (int i = 0; i < bookings.size(); i++) {
                returnBookedItem(bookings.get(i));
            }
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    @Step
    public void returnAllBookedItemsFromCompany() {
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
        LocalDateTime endDatePendingLocalDateTime = DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDate(), DateConstants.WW_PATTERN);
        endDatePendingLocalDateTime = DateUtils.addDaysAndHoursToDate(endDatePendingLocalDateTime, numberOfDaysToExtend, numberOfHoursToExtend);
        bookingExtendRequest.setEndDatePending(endDatePendingLocalDateTime.toString());
        bookingExtendRequest.setStatus(null);
        bookingExtendRequest.setClientTime(DateUtils.getCurrentDate().toString());
        bookingExtendRequest.setEndDatePending(DateFormatter.formatDate(endDatePendingLocalDateTime,
                DateConstants.WW_RETURN_DATE_PATTERN).toString());
        Booking bookingExtendResponse = updateResource(ApiUrlConstants.UPDATED_BOOKING, bookingExtendRequest, Booking.class, bookingExtendRequest.getId());
        bookingExtendRequest = (Booking)InstanceUtils.mergeObjects(bookingExtendRequest, bookingExtendResponse);
        bookingExtendRequest.setEndDate(DateFormatter.formatDate(
                (DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDatePending(), DateConstants.WW_RETURN_DATE_PATTERN)),
                DateConstants.WW_PATTERN));
        bookingExtendRequest.setBookingFullDaysNumber();
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingExtendRequest);
    }

    @Step
    public void extendBookingWithFailure(int numberOfDaysToExtend, int numberOfHoursToExtend) {
        Booking bookingExtendRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        LocalDateTime endDatePendingLocalDateTime = DateUtils.parseStringIntoDate(bookingExtendRequest.getEndDate(), DateConstants.WW_PATTERN);
        endDatePendingLocalDateTime = DateUtils.addDaysAndHoursToDate(endDatePendingLocalDateTime, numberOfDaysToExtend, numberOfHoursToExtend);
        bookingExtendRequest.setEndDatePending(endDatePendingLocalDateTime.toString());
        bookingExtendRequest.setBookingFullDaysNumber();
        bookingExtendRequest.setStatus(null);
        bookingExtendRequest.setClientTime(DateUtils.getCurrentDate().toString());
        bookingExtendRequest.setEndDatePending(DateFormatter.formatDate(endDatePendingLocalDateTime,
                DateConstants.WW_RETURN_DATE_PATTERN).toString());
        Booking bookingExtendResponse = updateResourceWithFailure(ApiUrlConstants.UPDATED_BOOKING, bookingExtendRequest, Booking.class,
                bookingExtendRequest.getId());
        bookingExtendRequest = (Booking)InstanceUtils.mergeObjects(bookingExtendRequest, bookingExtendResponse);
        bookingExtendRequest.setEndDate(bookingExtendRequest.getEndDatePending());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingExtendRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingExtendRequest);
    }
}