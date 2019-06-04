package com.steps.frontend;

import com.pages.ItemPage;
import com.tools.entities.Booking;

import net.thucydides.core.annotations.Step;

public class ItemSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private ItemPage itemPage;

    @Step
    public void navigateToCalendarDate(String startDate) {
        itemPage.navigateToCalendarDate(startDate);
    }

    @Step
    public int getFullDaysBookingNumber(String startDateString, String endDateString) {
        return itemPage.getTheFullDaysBookingNumber(startDateString, endDateString);
    }

    @Step
    public boolean isBookingPresentInCalendar(String startDateString, String endDateString) {
        return itemPage.isBookingPresentInCalendar(startDateString, endDateString);
    }

    @Step
    public boolean isFullDaysBookingPresentInCalendarHeader(Booking booking) {
        return itemPage.isFullDaysBookingPresentInCalendarHeader(booking);
    }

    @Step
    public void clickOnBooking(String startDateString) {
        itemPage.clickOnBooking(startDateString);
    }

    @Step
    public void uploadImageToItem(String filePath) {
        itemPage.uploadImageToItem(filePath);
    }

    @Step
    public void clickOnReturnBookingButton() {
        itemPage.clickOnReturnBookingButton();
    }

    @Step
    public void clickOnDeleteBookingButton() {
        itemPage.clickOnDeleteBookingButton();
    }

    @Step
    public void clickOnExtendBookingButton() {
        itemPage.clickOnExtendBookingButton();
    }

    @Step
    public void confirmBookingReturn() {
        itemPage.confirmBookingReturn();
    }

    @Step
    public void confirmBookingDetele() {
        itemPage.confirmBookingDetele();
    }

    @Step
    public void insertItemName(String itemName) {
        itemPage.insertItemName(itemName);
    }

    @Step
    public void selectItemCategory(String categoryName) {
        itemPage.selectItemCategory(categoryName);
    }

    @Step
    public void clickAddItemButton() {
        itemPage.clickAddItemButton();
    }

    @Step
    public void clickUpdateItemButton() {
        itemPage.clickUpdateItemButton();
    }
}
