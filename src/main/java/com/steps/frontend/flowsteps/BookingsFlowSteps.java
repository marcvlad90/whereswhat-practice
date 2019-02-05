package com.steps.frontend.flowsteps;

import com.steps.frontend.AbstractSteps;
import com.steps.frontend.BookingsSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ItemSteps;
import com.steps.frontend.ItemsSteps;
import com.tools.constants.Constants;
import com.tools.constants.DateConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Booking;
import com.tools.entities.Item;
import com.tools.utils.DateFormatter;
import com.tools.utils.DateUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import org.junit.Assert;
import org.openqa.selenium.By;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookingsFlowSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    @Steps
    private HeaderSteps headerSteps;
    @Steps
    private BookingsSteps bookingsSteps;
    @Steps
    private ItemsSteps itemsSteps;
    @Steps
    private ItemSteps itemSteps;

    @StepGroup
    public void checkIfBookingIsPresentOrNotByCheckingDetails(boolean shouldBePresent) {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_BOOKINGS);
        bookingsSteps.navigateToTheFirstBookingsTabFound(Constants.BOOKINGS_TAB_MY_BOOKINGS, Constants.BOOKINGS_TAB_ALL_BOOKINGS);
        bookingsSteps.checkIfBookingIsPresentOrNotByCheckingDetails(booking, shouldBePresent);
    }

    @StepGroup
    public void checkIfBookingIsPresentOrNotInTheCalendar(boolean shouldBePresent) {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(booking.getItem().getTitle());
        itemsSteps.clickOnItem(booking.getItem().getTitle());
        if (shouldBePresent) {
            Assert.assertTrue("The booking was not found in the calendar!",
                    itemSteps.isBookingPresentInCalendar(booking.getStartDate(), booking.getEndDate()));
            Assert.assertTrue("The booking days number is incorrect!",
                    itemSteps.getFullDaysBookingNumber(booking.getStartDate(), booking.getEndDate()) == booking
                    .getFullDaysBookingNumber());
        } else {
            Assert.assertFalse("The booking was found in the calendar and it should not!",
                    itemSteps.isBookingPresentInCalendar(booking.getStartDate(), booking.getEndDate()));
        }
    }

    @StepGroup
    public void checkIfBookingsArePresentOrNotInTheCalendar(boolean shouldBePresent) {
        List<Booking> bookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(bookings.get(0).getItem().getTitle());
        itemsSteps.clickOnItem(bookings.get(0).getItem().getTitle());
        for (int i = 0; i < bookings.size(); i++) {
            if ((i > 0) && !bookings.get(i).getItem().getTitle().contentEquals(bookings.get(i - 1).getItem().getTitle())) {
                headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
                itemsSteps.searchForItem(bookings.get(i).getItem().getTitle());
                itemsSteps.clickOnItem(bookings.get(i).getItem().getTitle());
            }
            if (shouldBePresent) {
                Assert.assertTrue("The booking was not found in the calendar!",
                        itemSteps.isBookingPresentInCalendar(bookings.get(i).getStartDate(), bookings.get(i).getEndDate()));
                Assert.assertTrue("The booking days number is incorrect!",
                        itemSteps.getFullDaysBookingNumber(bookings.get(i).getStartDate(), bookings.get(i).getEndDate()) == bookings.get(i)
                        .getFullDaysBookingNumber());
            } else {
                Assert.assertFalse("The booking was found in the calendar and it should not!",
                        itemSteps.isBookingPresentInCalendar(bookings.get(i).getStartDate(), bookings.get(i).getEndDate()));
            }
        }
    }

    @StepGroup
    public void checkIfBookingsArePresentOrNotByCheckingDetails(boolean shouldBePresent) {
        List<Booking> bookings = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKINGS);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_BOOKINGS);
        bookingsSteps.navigateToTheFirstBookingsTabFound(Constants.BOOKINGS_TAB_MY_BOOKINGS, Constants.BOOKINGS_TAB_ALL_BOOKINGS);
        for (Booking booking : bookings) {
            bookingsSteps.checkIfBookingIsPresentOrNotByCheckingDetails(booking, shouldBePresent);
        }
    }

    @StepGroup
    public void acceptItemBooking() {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_BOOKINGS);
        bookingsSteps.navigateToBookingsTab(Constants.BOOKINGS_TAB_BOOKING_REQUESTS);
        bookingsSteps.acceptBooking(booking);
        booking.setStatus("accepted");
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, booking);
    }

    @StepGroup
    public void declineItemBooking() {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_BOOKINGS);
        bookingsSteps.navigateToBookingsTab(Constants.BOOKINGS_TAB_BOOKING_REQUESTS);
        bookingsSteps.declineBooking(booking);
        booking.setStatus("declined");
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, booking);
    }

    @StepGroup
    public void declineItemBookingExtension() {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_BOOKINGS);
        bookingsSteps.navigateToBookingsTab(Constants.BOOKINGS_TAB_BOOKING_REQUESTS);
        bookingsSteps.declineBooking(booking);
        booking.setStatus("accepted");
        booking.setEndDate(booking.getEndDateInitial());
        LocalDateTime startDate = DateUtils.parseStringIntoDate((booking.getStartDate()), DateConstants.WW_PATTERN);
        LocalDateTime newEndDate = DateUtils.parseStringIntoDate((booking.getEndDateInitial()), DateConstants.WW_PATTERN);
        booking.setFullDaysBookingNumber(ChronoUnit.DAYS.between(startDate.plusMinutes(60 - startDate.getMinute()).plusHours(23 - startDate.getHour()),
                newEndDate.minusMinutes(newEndDate.getMinute()).minusHours(newEndDate.getHour())));
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, booking);
    }

    @StepGroup
    public void returnAllBookingsOfTheItem() {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_BOOKINGS);
        bookingsSteps.navigateToTheFirstBookingsTabFound(Constants.BOOKINGS_TAB_MY_BOOKINGS, Constants.BOOKINGS_TAB_ALL_BOOKINGS);
        bookingsSteps.returnAllBookingsOfAnItem(item.getTitle());
    }

    @StepGroup
    public void returnAllBookingsOfTheItemsFromSession() {
        List<Item> items = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_BOOKINGS);
        bookingsSteps.navigateToTheFirstBookingsTabFound(Constants.BOOKINGS_TAB_MY_BOOKINGS, Constants.BOOKINGS_TAB_ALL_BOOKINGS);
        for (Item item : items) {
            bookingsSteps.returnAllBookingsOfAnItem(item.getTitle());
        }
    }

    @StepGroup
    public void bookItem(int howManyDaysFromNow, int howManyHoursFromNow, int howManyMinutesFromNow, int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnBookButton(item.getTitle());
        bookingsSteps.createBooking(howManyDaysFromNow, howManyHoursFromNow, howManyMinutesFromNow, forHowManyDays, forHowManyHours,
                forHowManyMinutes);
    }

    @StepGroup
    public void bookItemWithDefaultSetLength() {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnBookButton(item.getTitle());
        bookingsSteps.createDefaultLengthBooking();
    }

    @StepGroup
    public void bookItemForWholeDays(int howManyDaysFromNow, int forHowManyDays) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnBookButton(item.getTitle());
        bookingsSteps.createBooking(howManyDaysFromNow, forHowManyDays);
    }

    @StepGroup
    public void bookItemFromCalendar(int howManyDaysFromNow, int howManyHoursFromNow, int howManyMinutesFromNow, int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnItem(item.getTitle());
        getDriver().findElement(By.cssSelector(".fc-day.fc-widget-content.fc-future")).click();
        bookingsSteps.createBooking(howManyDaysFromNow, howManyHoursFromNow, howManyMinutesFromNow, forHowManyDays, forHowManyHours,
                forHowManyMinutes);
    }

    @StepGroup
    public void bookItemInThePast(int howManyDaysFromNow, int howManyHoursFromNow, int howManyMinutesFromNow, int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnBookButton(item.getTitle());
        bookingsSteps.createBooking(howManyDaysFromNow, howManyHoursFromNow, howManyMinutesFromNow, forHowManyDays, forHowManyHours,
                forHowManyMinutes);

    }

    @StepGroup
    public void bookItemWithFailure(int howManyDaysFromNow, int howManyHoursFromNow, int howManyMinutesFromNow, int forHowManyDays,
            int forHowManyHours, int forHowManyMinutes) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnBookButton(item.getTitle());
        bookingsSteps.createBooking(howManyDaysFromNow, howManyHoursFromNow, howManyMinutesFromNow, forHowManyDays, forHowManyHours,
                forHowManyMinutes);
        bookingsSteps.checkThatFailingBookingAlertMessageIsDisplayed();
        bookingsSteps.getDriver().navigate().refresh();
    }

    @StepGroup
    public void extendBookedItemViaCalendar(int numberOfDaysToExtend, int numberOfHoursToExtend) {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        booking.setEndDate(DateFormatter.formatDate(
                DateUtils.addHoursToDate(numberOfHoursToExtend,
                        DateUtils.parseStringIntoDate(booking.getEndDate(), DateConstants.WW_PATTERN)),
                        DateConstants.WW_PATTERN)
                        .toString());
        booking.setFullDaysBookingNumber(booking.getFullDaysBookingNumber() + numberOfDaysToExtend);
        booking.setEndDate(DateFormatter
                .formatDate(
                        DateUtils.addDaysToDate(numberOfDaysToExtend,
                                DateUtils.parseStringIntoDate(booking.getEndDate(), DateConstants.WW_PATTERN)),
                                DateConstants.WW_PATTERN)
                                .toString());
        LocalDateTime startDate = DateUtils.parseStringIntoDate((booking.getStartDate()), DateConstants.WW_PATTERN);
        LocalDateTime newEndDate = DateUtils.parseStringIntoDate((booking.getEndDate()), DateConstants.WW_PATTERN);
        if (ChronoUnit.DAYS.between(startDate, newEndDate) > 0) {
            booking.setFullDaysBookingNumber(ChronoUnit.DAYS.between(startDate.plusMinutes(60 - startDate.getMinute()).plusHours(23 - startDate.getHour()),
                    newEndDate.minusMinutes(newEndDate.getMinute()).minusHours(newEndDate.getHour())));
        } else {
            booking.setFullDaysBookingNumber(0);
        }
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(booking.getItem().getTitle());
        itemsSteps.clickOnItem(booking.getItem().getTitle());
        itemSteps.navigateToCalendarDate(booking.getStartDate());
        itemSteps.clickOnBooking(booking.getStartDate());
        itemSteps.clickOnExtendBookingButton();
        bookingsSteps.extendBooking(booking.getEndDate());
        if (booking.getUser().getRole().equals("company_user") && booking.getItem().getCategory().isNeedsApproval()) {
            booking.setStatus("pending");
        }
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, booking);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, booking);
    }

    @StepGroup
    public void extendBookedItemWithFailureViaCalendar(int numberOfDaysToExtend, int numberOfHoursToExtend) {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        booking.setEndDate(DateFormatter.formatDate(
                DateUtils.addHoursToDate(numberOfHoursToExtend,
                        DateUtils.parseStringIntoDate(booking.getEndDate(), DateConstants.WW_PATTERN)),
                        DateConstants.WW_PATTERN)
                        .toString());
        booking.setEndDate(DateFormatter
                .formatDate(
                        DateUtils.addDaysToDate(numberOfDaysToExtend,
                                DateUtils.parseStringIntoDate(booking.getEndDate(), DateConstants.WW_PATTERN)),
                                DateConstants.WW_PATTERN)
                                .toString());
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(booking.getItem().getTitle());
        itemsSteps.clickOnItem(booking.getItem().getTitle());
        itemSteps.navigateToCalendarDate(booking.getStartDate());
        itemSteps.clickOnBooking(booking.getStartDate());
        itemSteps.clickOnExtendBookingButton();
        bookingsSteps.extendBooking(booking.getEndDate());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, booking);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, booking);
        bookingsSteps.checkThatFailingBookingAlertMessageIsDisplayed();
        bookingsSteps.getDriver().navigate().refresh();

    }

    @StepGroup
    public void returnBookedItemViaCalendar() {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(booking.getItem().getTitle());
        itemsSteps.clickOnItem(booking.getItem().getTitle());
        itemSteps.navigateToCalendarDate(booking.getStartDate());
        itemSteps.clickOnBooking(booking.getStartDate());
        itemSteps.clickOnReturnBookingButton();
        booking.setEndDate(DateUtils.parseStringIntoDate(booking.getClientTime(), DateConstants.WW_PATTERN).plusHours(1).toString());
        if (DateUtils.getCurrentDate().getSecond() > 57) {
            waitABit(3000);
        }
        itemSteps.confirmBookingReturn();
        booking.setStatus("Completed");
        booking.setCanReturn(false);
        String newEndDate = DateFormatter.formatDate(DateUtils.addHoursToDate(1, DateUtils.getCurrentDate()), DateConstants.WW_PATTERN).toString();
        booking.setEndDate(newEndDate);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.BOOKING, booking);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.BOOKINGS, booking);
    }

    @StepGroup
    public void deleteBookedItemViaCalendar() {
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(booking.getItem().getTitle());
        itemsSteps.clickOnItem(booking.getItem().getTitle());
        itemSteps.navigateToCalendarDate(booking.getStartDate());
        itemSteps.clickOnBooking(booking.getStartDate());
        itemSteps.clickOnDeleteBookingButton();
        itemSteps.confirmBookingDetele();
    }
}
