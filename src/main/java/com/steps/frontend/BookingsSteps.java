package com.steps.frontend;

import com.pages.BookingsPage;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.factories.BookingFactory;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

public class BookingsSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;

    private BookingsPage bookingsPage;

    @Step
    public void createBooking(int howManyDaysFromNow, int howManyHoursFromNow, int howManyMinutesFromNow, int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        Booking bookingRequest = BookingFactory.getApiParameterizedBookingInstance(howManyDaysFromNow, howManyHoursFromNow, howManyMinutesFromNow,
                forHowManyDays, forHowManyHours, forHowManyMinutes);
        bookingsPage.createBooking(bookingRequest.getStartDate(), bookingRequest.getEndDate());
        bookingRequest.setStatus("Accepted");
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
    }

    @Step
    public void createBooking(int howManyDaysFromNow, int forHowManyDays) {
        Booking bookingRequest = BookingFactory.getApiWholeDaysBookingInstance(howManyDaysFromNow, forHowManyDays);
        bookingsPage.createBooking(bookingRequest.getStartDate(), bookingRequest.getEndDate());
        bookingRequest.setStatus("Accepted");
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
    }

    @Step
    public void createDefaultLengthBooking() {
        Booking bookingRequest = BookingFactory.getDefaultLengthBooking();
        bookingsPage.clickSaveBookingButton();
        bookingRequest.setStatus("Accepted");
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, bookingRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, bookingRequest);
    }

    @Step
    public void checkThatFailingBookingAlertMessageIsDisplayed() {
        Assert.assertTrue(
                "The failing booking error message is not present!",
                bookingsPage.checkIfElementExists(".message-alert") ||
                bookingsPage.checkIfElementExists(".noty_text"));
    }

    @Step
    public void returnAllBookingsOfAnItem(String itemName) {
        bookingsPage.returnAllBookingsOfAnItem(itemName);
    }

    @Step
    public void extendBooking(String endDate) {
        bookingsPage.extendBooking(endDate);
    }

    @Step
    public void navigateToBookingsTab(String tab) {
        bookingsPage.navigateToBookingsTab(tab);
    }

    @Step
    public void acceptBooking(Booking booking) {
        bookingsPage.acceptBooking(booking);
    }

    @Step
    public void declineBooking(Booking booking) {
        bookingsPage.declineBooking(booking);
    }

    @Step
    public void navigateToTheFirstBookingsTabFound(String... tabs) {
        bookingsPage.navigateToTheFirstBookingsTabFound(tabs);
    }

    @Step
    public void checkIfBookingIsPresentOrNotByCheckingDetails(Booking booking, boolean shouldBePresent) {
        if (shouldBePresent) {
            Assert.assertTrue("The booking was not found!", bookingsPage.checkIfElementExists(bookingsPage.getSpecificBookingContainer(booking)));
        } else {
            Assert.assertFalse("The booking was found!", bookingsPage.checkIfElementExists(bookingsPage.getSpecificBookingContainer(booking)));
        }
    }
}
