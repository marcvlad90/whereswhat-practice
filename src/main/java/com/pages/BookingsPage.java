package com.pages;

import com.tools.constants.Constants;
import com.tools.entities.Booking;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class BookingsPage extends AbstractPage {
    @FindBy(css = "div[style*='block'] div.datepicker")
    private WebElement calendarContainer;
    @FindBy(css = "input#booking_start_date1")
    private WebElement startDatePickerElement;
    @FindBy(css = "input#booking_start_date2")
    private WebElement endDatePickerElement;
    @FindBy(id = "create-booking-action")
    private WebElement saveBookingButton;
    @FindBy(id = "edit-booking-action")
    private WebElement saveBookingChangesButton;
    @FindBy(css = ".booking_start_hour")
    private WebElement startHourField;
    @FindBy(css = ".booking_end_hour")
    private WebElement endHourField;
    @FindBy(css = "#item-return-item")
    private WebElement returnItemConfirmButton;
    @FindBy(id = "item-confirm-decline-booking")
    private WebElement declineBookingConfirmButton;
    private String bookingsTabsCssSelector = ".clearFilter a[aria-controls='textToReplace()']";
    private final String calendarYearsListCssSelector = ".datepicker-years tr>td>span:not([class='disabled']):not([class*='old']):not([class*='new'])";
    private final String calendarMonthsListCssSelector = ".datepicker-months tr>td>span:not([class*='disabled']):not([class*='old']):not([class*='new'])";
    private final String calendarDaysListCssSelector = ".datepicker-days td:not([class*='disabled']):not([class*='old']):not([class*='new'])";
    private final String hourPickerFieldValuesCssSelector = ".ui-timepicker-list>li";
    private final String bookingsContainersCssSelector = "ul[class*='bookings-listing'] div[class*='item-booking-container']";
    private final String returnItemButtonCssSelector = "button[class*='return-item']";
    private final String acceptItemButtonCssSelector = "button[class*='accept-booking']";
    private final String declineItemButtonCssSelector = "button[class*='decline-booking']";
    private final String spinnerElementCssSelector = ".spinner";

    public void clickSaveBookingButton() {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        waitForElementToBeClickable(saveBookingButton, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        clickOnElementUsingJavascript(saveBookingButton);
        //        element(saveBookingButton).click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
    }

    public void selectHour(WebElement hourField, String date) {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        Actions actions = new Actions(getDriver());
        clickOnElementUsingJavascript(hourField);
        String hour = DateUtils.getHourFromDate(date);
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        waitForListToLoad(getDriver().findElements(By.cssSelector(hourPickerFieldValuesCssSelector)), Constants.WAIT_TIME_MAXIMUM_IN_SECONDS, true);
        actions.moveToElement(getDriver().findElements(By.cssSelector(hourPickerFieldValuesCssSelector)).get(0)).build().perform();
        clickOnElementFromList(hourPickerFieldValuesCssSelector, hour);
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
    }

    public void selectItemFromPickerList(String itemsListCssSelector, String itemValue) {
        boolean itemFound = false;
        while (!itemFound) {
            List<WebElement> items = calendarContainer.findElements(By.cssSelector(itemsListCssSelector));
            for (WebElement item : items) {
                if (item.getText().toLowerCase().contentEquals(itemValue.toLowerCase())) {
                    item.click();
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                WebElement nextButton = calendarContainer.findElement(By.cssSelector("div[style*='display: block;'] .pickerHeader .next"));
                clickOnElementUsingJavascript(nextButton);
            }
        }
    }

    public void selectDate(WebElement dateField, String date) {
        selectTheDateYear(dateField, date);
        selectTheDateMonth(date);
        selectTheDateDay(date);
    }

    public void acceptBooking(Booking booking) {
        getSpecificBookingContainer(booking).findElement(By.cssSelector(acceptItemButtonCssSelector)).click();
    }

    public void declineBooking(Booking booking) {
        getSpecificBookingContainer(booking).findElement(By.cssSelector(declineItemButtonCssSelector)).click();
        declineBookingConfirmButton.click();
    }

    public void selectTheDateYear(WebElement dateField, String date) {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        String yearFromDate = DateUtils.getYearFromDate(date);
        clickOnElementUsingJavascript(dateField);
        WebElement daysPickerSwitch = getDriver().findElement(By.cssSelector("div[class*='picker-open'] .picker-switch"));
        element(daysPickerSwitch).waitUntilClickable();
        daysPickerSwitch.click();
        WebElement monthsPickerSwitch = getDriver().findElement(By.cssSelector("div[style*='block'] .datepicker-months .picker-switch"));
        element(monthsPickerSwitch).waitUntilClickable();
        monthsPickerSwitch.click();
        selectItemFromPickerList(calendarYearsListCssSelector, yearFromDate);
    }

    public void selectTheDateMonth(String date) {
        String monthFromDate = DateUtils.getMonthFromDate(date);
        selectItemFromPickerList(calendarMonthsListCssSelector, monthFromDate);
    }

    public void selectTheDateDay(String date) {
        String day = DateUtils.getDayFromDate(date).replaceFirst("^0*", "");
        selectItemFromPickerList(calendarDaysListCssSelector, day);
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
    }

    public void createBooking(String startDate, String endDate) {
        selectDate(startDatePickerElement, startDate);
        selectHour(startHourField, startDate);
        selectDate(endDatePickerElement, endDate);
        selectHour(endHourField, endDate);
        saveBookingButton.click();
    }

    public void createBookingInThePast(String startDate, String endDate) {
        selectDate(startDatePickerElement, startDate);
        selectHour(startHourField, startDate);
        selectDate(endDatePickerElement, endDate);
        selectHour(endHourField, endDate);
        saveBookingButton.click();
    }

    public void extendBooking(String endDate) {
        selectDate(endDatePickerElement, endDate);
        selectHour(endHourField, endDate);
        clickOnElementUsingJavascript(saveBookingChangesButton);
    }

    public void navigateToBookingsTab(String tab) {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        WebElement tabElement = getDriver()
                .findElement(By.cssSelector(bookingsTabsCssSelector.replace("textToReplace()", tab.toLowerCase().replace(" ", "-"))));
        waitForElementToBeClickable(tabElement, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        clickOnElementUsingJavascript(tabElement);
    }

    public void navigateToTheFirstBookingsTabFound(String... tabs) {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        for (String tab : tabs) {
            if (checkIfElementExists(bookingsTabsCssSelector.replace("textToReplace()", tab.toLowerCase().replace(" ", "-")))) {
                navigateToBookingsTab(tab);
                break;
            }
        }
    }

    public List<WebElement> getAllBookingsContainersOfAnItem(String itemName) {
        if (!containsText("No bookings to be displayed")) {
            waitForListToLoad(bookingsContainersCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS, false);
        }
        List<WebElement> bookingContainers = getDriver().findElements(By.cssSelector(bookingsContainersCssSelector));
        List<WebElement> itemBookingsContainers = new ArrayList<WebElement>();
        for (WebElement bookingContainer : bookingContainers) {
            if (bookingContainer.findElement(By.cssSelector(".booking-details-container a:nth-child(1)")).getText()
                    .equalsIgnoreCase(itemName)) {
                itemBookingsContainers.add(bookingContainer);
            }
        }
        return itemBookingsContainers;
    }

    public void returnAllBookingsOfAnItem(String itemName) {
        List<WebElement> itemBookingsContainers = getAllBookingsContainersOfAnItem(itemName);
        for (WebElement itemBookingContainer : itemBookingsContainers) {
            if (checkIfChildElementExists(itemBookingContainer, returnItemButtonCssSelector)) {
                clickOnElementUsingJavascript(itemBookingContainer.findElement(By.cssSelector(returnItemButtonCssSelector)));
                confirmBookingReturn();
                waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
            }
        }
    }

    public void confirmBookingReturn() {
        clickOnElementUsingJavascript(returnItemConfirmButton);
    }

    public boolean checkThatBookingContainerHasTheCorrectDetail(WebElement bookingContainer, String bookingDetailName, String bookingDetailValue) {
        List<WebElement> bookingDetailsLines = bookingContainer.findElements(By.cssSelector(".booking-details-container>ul>li"));
        for (WebElement bookingDetailsLine : bookingDetailsLines) {
            if (bookingDetailsLine.getText().contains(bookingDetailName)) {
                List<WebElement> detailsParts = bookingDetailsLine.findElements(By.cssSelector("span"));
                if (detailsParts.size() > 1) {
                    for (int i = 0; i < (detailsParts.size() - 1); i++) {
                        System.out.println(String.format("Compare existing %s value which is %s with expected %s", detailsParts.get(i).getText(), detailsParts
                                .get(i + 1).getText(), bookingDetailValue));
                        if (detailsParts.get(i).getText().contentEquals(bookingDetailName)
                                && detailsParts.get(i + 1).getText().contentEquals(bookingDetailValue)) {
                            return true;
                        }
                    }
                }
                else {
                    if (bookingDetailsLine.getText().contentEquals(bookingDetailName + " " + bookingDetailValue)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public WebElement getSpecificBookingContainer(Booking booking) {
        int numberOfTries = 0;
        do {
            System.out.println("try number " + numberOfTries);
            numberOfTries++;
            List<WebElement> bookingContainers = getDriver().findElements(By.cssSelector(bookingsContainersCssSelector));
            for (WebElement bookingContainer : bookingContainers) {
                if (bookingContainer.findElement(By.cssSelector(".booking-details-container a")).getText()
                        .equalsIgnoreCase(booking.getItem().getTitle())) {
                    System.out.println("Booking title matches!");
                    if (bookingContainer.findElement(By.cssSelector(".status-label")).getText().equalsIgnoreCase(booking.getStatus())) {
                        System.out.println("Booking status matches!");
                        if (checkThatBookingContainerHasTheCorrectDetail(bookingContainer, "Booked from", booking.getStartDate().replace(", 0", ", "))) {
                            System.out.println("Booking start date matches!");
                            if (checkThatBookingContainerHasTheCorrectDetail(bookingContainer, "Request to extend until", booking.getEndDate()
                                    .replace(", 0", ", "))
                                    || checkThatBookingContainerHasTheCorrectDetail(bookingContainer, "to", booking.getEndDate().replace(", 0", ", "))) {
                                System.out.println("Booking end date matches!");
                                if (checkThatBookingContainerHasTheCorrectDetail(bookingContainer, "Booked by", booking.getUser().getName())) {
                                    System.out.println("Booking user matches!");
                                    return bookingContainer;
                                }
                            }
                        }
                    }
                }
            }
        } while ((numberOfTries < 3) && !containsText("No bookings to be displayed"));
        return null;
    }
}