package com.pages;

import com.tools.constants.Constants;
import com.tools.constants.DateConstants;
import com.tools.entities.Booking;
import com.tools.utils.DateUtils;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class ItemPage extends AbstractPage {
    @FindBy(id = "item_title")
    private WebElement itemNameField;
    @FindBy(id = "item-category-id")
    private WebElement itemCategoryDrownDown;
    @FindBy(css = "#add_item .btn.btn-success")
    private WebElement addItemButon;
    @FindBy(css = "#edit_item .btn.btn-success")
    private WebElement updateItemButon;
    @FindBy(css = ".fc-toolbar .fc-left  .fc-button-group button[class*='fc-corner-right']")
    private WebElement nextIntervalElement;
    @FindBy(css = ".fc-toolbar .fc-left  .fc-button-group button[class*='fc-corner-left']")
    private WebElement previousIntervalElement;
    @FindBy(css = "button[class*='return-item']")
    private WebElement returnItemButton;
    @FindBy(css = "button[class*='popover-edit-booking']")
    private WebElement editItemBookingButton;
    @FindBy(css = "i[class*='popover-delete-booking']")
    private WebElement deleteItemBookingButton;
    @FindBy(id = "popover-return-item")
    private WebElement returnItemConfirmButton;
    @FindBy(id = "popover-delete-booking")
    private WebElement deleteItemConfirmButton;
    @FindBy(css = "button[class*='upload-image-modal']")
    private WebElement selectImageToUploadButton;
    @FindBy(css = ".modal-footer .crop-n-save-image")
    private WebElement uploadImageOkButton;
    private final String spinnerElementCssSelector = ".spinner";

    public void uploadImageToItem(String filePath) {
        selectImageToUploadButton.click();
        final File fNewTwo = new File(filePath);
        final String fileName = fNewTwo.getAbsolutePath();
        getDriver().findElement(By.cssSelector("#select-image")).sendKeys(fileName);
        uploadImageOkButton.click();
        clickUpdateItemButton();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
    }

    public void insertItemName(String itemName) {
        waitForElementToAppear(itemNameField, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        element(itemNameField).clear();
        element(itemNameField).sendKeys(itemName);
    }

    public void selectItemCategory(String categoryName) {
        element(itemCategoryDrownDown).selectByVisibleText(categoryName);
    }

    public void clickAddItemButton() {
        addItemButon.click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        getDriver().navigate().refresh();

    }

    public void clickUpdateItemButton() {
        updateItemButon.click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        getDriver().navigate().refresh();
    }

    public LocalDateTime getIntervalStartDate() {
        String dateIntervalValues = getDriver().findElement(By.cssSelector(".fc-toolbar .fc-center")).getText();
        String[] intervalParts = dateIntervalValues.split(" ");
        if ((intervalParts.length < 7)) {
            LocalDateTime intervalStartDate = DateUtils.parseStringIntoDate(intervalParts[0] + " " + intervalParts[1] + " "
                    + intervalParts[intervalParts.length - 1] + ", 12:00 AM",
                    DateConstants.WW_PATTERN);
            return intervalStartDate;
        }
        else {
            LocalDateTime intervalStartDate = DateUtils.parseStringIntoDate(intervalParts[0] + " " + intervalParts[1] + " "
                    + intervalParts[2] + ", 12:00 AM",
                    DateConstants.WW_PATTERN);
            return intervalStartDate;
        }
    }

    public int getAllCalendarHeaderConsecutiveDisplayedFullDaysOfBooking(String endDate) {
        return Integer.parseInt(getDriver().findElement(By.cssSelector(".fc-content-skeleton tr>td.fc-event-container")).getAttribute("colspan"));
    }

    public int getAllConsecutiveDisplayedFullDaysOfBooking(String endDate) {
        int numberOfFullDays = 0;
        List<WebElement> dayDivs = getDriver().findElements(By.cssSelector(".fc-time-grid-event"));
        for (int i = 0; i < dayDivs.size(); i++) {
            if (dayDivs.get(i).getAttribute("class").contains("fc-not-start fc-not-end")) {
                numberOfFullDays++;
            }
            else if (dayDivs.get(i).getAttribute("class").contains("fc-end")
                    && dayDivs.get(i).findElement(By.cssSelector(".fc-content .fc-time")).getAttribute("data-full")
                    .contains(endDate.split(", ")[1].replaceFirst("^0+(?!$)", ""))) {
                return numberOfFullDays;
            } else if (dayDivs.get(i).getAttribute("class").contains("fc-short")
                    && dayDivs.get(i).findElement(By.cssSelector(".fc-content .fc-time")).getAttribute("data-full")
                    .contains(endDate.split(", ")[1].replaceFirst("^0+(?!$)", ""))) {
                return 0;
            }
        }
        return numberOfFullDays;
    }

    public int getTheCalendarHeaderFullDaysBookingNumber(String startDateString, String endDateString) {
        navigateToCalendarDate(startDateString);
        int numberOfFullDays = 0;
        LocalDateTime endDateValue = DateUtils.parseStringIntoDate(endDateString, DateConstants.WW_PATTERN);
        while (getIntervalStartDate().isAfter(endDateValue)) {
            previousIntervalElement.click();
        }
        numberOfFullDays += getAllCalendarHeaderConsecutiveDisplayedFullDaysOfBooking(endDateString);
        while (!getIntervalStartDate().plusDays(7).isAfter(endDateValue)) {
            nextIntervalElement.click();
            numberOfFullDays += getAllCalendarHeaderConsecutiveDisplayedFullDaysOfBooking(endDateString);
        }
        return numberOfFullDays;
    }

    public int getTheFullDaysBookingNumber(String startDateString, String endDateString) {
        navigateToCalendarDate(startDateString);
        int numberOfFullDays = 0;
        LocalDateTime endDateValue = DateUtils.parseStringIntoDate(endDateString, DateConstants.WW_PATTERN);
        while (getIntervalStartDate().isAfter(endDateValue)) {
            previousIntervalElement.click();
        }
        numberOfFullDays += getAllConsecutiveDisplayedFullDaysOfBooking(endDateString);
        while (!getIntervalStartDate().plusDays(7).isAfter(endDateValue)) {
            nextIntervalElement.click();
            numberOfFullDays += getAllConsecutiveDisplayedFullDaysOfBooking(endDateString);
        }
        return numberOfFullDays;
    }

    public void navigateToCalendarDate(String date) {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        LocalDateTime expectedDate = DateUtils.parseStringIntoDate(date, DateConstants.WW_PATTERN);
        while (!getIntervalStartDate().plusDays(7).isAfter(expectedDate)) {
            nextIntervalElement.click();
        }
        while (getIntervalStartDate().isAfter(expectedDate)) {
            previousIntervalElement.click();
        }
    }

    public boolean isBookingPresentInCalendar(String startDateString, String endDateString) {
        boolean isBookingPresent = false;
        navigateToCalendarDate(startDateString);
        if (checkIfElementExists("a[class*='fc-time-grid-event'] .fc-time[data-full*='" + startDateString.split(", ")[1].replaceFirst("^0+(?!$)", "") + " - "
                + "']")) {
            getTheFullDaysBookingNumber(startDateString, endDateString);
            if (checkIfElementExists("a[class*='fc-time-grid-event'] .fc-time[data-full*=' - "
                    + endDateString.split(", ")[1].replaceFirst("^0+(?!$)", "") + "']")) {
                isBookingPresent = true;
            }
        }
        return isBookingPresent;
    }

    public boolean isFullDaysBookingPresentInCalendarHeader(Booking booking) {
        int calendarHeaderFullDaysBookingNumber = getTheCalendarHeaderFullDaysBookingNumber(booking.getStartDate(), booking.getEndDate());
        if (calendarHeaderFullDaysBookingNumber == booking.getFullDaysBookingNumber()) {
            return true;
        }
        return false;
    }

    public int getCalendarHeaderFullDaysBookingNumber(String startDateString, String endDateString) {
        return getTheCalendarHeaderFullDaysBookingNumber(startDateString, endDateString);
    }

    public int getFullDaysBookingNumber(String startDateString, String endDateString) {
        return getTheFullDaysBookingNumber(startDateString, endDateString);
    }

    public void clickOnBooking(String startDateString) {
        WebElement calendarBooking = getDriver().findElement(
                By.cssSelector("a[class*='fc-start'] .fc-time[data-start='" + startDateString.split(", ")[1].replaceFirst("^0+(?!$)", "") + "']"));
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].click();", calendarBooking);
    }

    public void clickOnReturnBookingButton() {
        returnItemButton.click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
    }

    public void clickOnDeleteBookingButton() {
        deleteItemBookingButton.click();
    }

    public void confirmBookingReturn() {
        returnItemConfirmButton.click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
    }

    public void confirmBookingDetele() {
        deleteItemConfirmButton.click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
    }

    public void clickOnExtendBookingButton() {
        editItemBookingButton.click();
    }

}
