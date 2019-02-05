package com.tests.apiCallsUIValidation;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.flowsteps.BookingsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test040ApiUiValidationDeclineSupervisionedBooking extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private BookingsFlowSteps bookingsFlowSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private HeaderSteps headerSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createSupervisionedCategory();
        apiItemSteps.createItem();
        apiLoginSteps.loginAsRegularUser();
        loginFlowSteps.loginAsRegularUser();
    }

    @Test
    public void test040ApiUiValidationDeclineSupervisionedBooking() {
        apiBookingsSteps.bookItem(1, 1, 1, 1, 1, 1);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        apiLoginSteps.loginAsAdmin();
        apiBookingsSteps.declineItemBooking();
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(false);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
    }
}
