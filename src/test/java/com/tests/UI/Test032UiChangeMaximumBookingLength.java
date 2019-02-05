package com.tests.UI;

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
public class Test032UiChangeMaximumBookingLength extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private BookingsFlowSteps bookingsFlowSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
    }

    @Test
    public void test032UiChangeMaximumBookingLength() {
        apiCategorySteps.changeDefaultBookingLengthDaysValue(3);
        apiCategorySteps.changeMaxBookingLengthDaysValue(6);
        bookingsFlowSteps.bookItemWithFailure(1, 1, 1, 6, 0, 5);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(false);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(false);
        bookingsFlowSteps.bookItem(1, 1, 1, 6, 0, 0);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiCategorySteps.changeDefaultBookingLengthHoursValue(30);
        apiCategorySteps.changeMaxBookingLengthHoursValue(60);
        bookingsFlowSteps.bookItemWithFailure(1, 1, 1, 0, 60, 5);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(false);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(false);
        bookingsFlowSteps.bookItem(1, 1, 1, 0, 60, 0);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);

    }
}
