package com.tests.apiCallsUIValidation;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.BookingsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test005ApiUiValidationReturnItem extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiBookingsSteps apiBookingSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private BookingsFlowSteps bookingFlowSteps;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiBookingSteps.bookItemFromNow(1, 1, 1);
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiBookingSteps.bookItemFromNow(1, 1, 1);
    }

    @Test
    public void test005ApiUiValidationReturnItem() {
        apiBookingSteps.returnBookedItem();
        bookingFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
        apiBookingSteps.returnAllBookedItems();
        bookingFlowSteps.checkIfBookingsArePresentOrNotByCheckingDetails(true);
        bookingFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
    }
}
