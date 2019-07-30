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
public class Test006UiExtendBooking extends BaseTest {
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
        apiBookingsSteps.bookItemFromNow(1, 1, 1);
    }

    @Test
    public void test006UiExtendBooking() {
        bookingsFlowSteps.extendBookedItemViaCalendar(1, 1);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
        bookingsFlowSteps.extendBookedItemViaCalendar(10, 10);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
        bookingsFlowSteps.extendBookedItemViaCalendar(-5, -5);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
    }
}
