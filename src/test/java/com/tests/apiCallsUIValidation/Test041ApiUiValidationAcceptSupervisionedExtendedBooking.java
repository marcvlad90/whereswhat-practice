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
public class Test041ApiUiValidationAcceptSupervisionedExtendedBooking extends BaseTest {
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
        apiCategorySteps.createSupervisionedCategory();
        apiItemSteps.createItem();
        apiLoginSteps.loginAsRegularUser();
        apiBookingsSteps.bookItemFromNow(1, 1, 1);
        apiLoginSteps.loginAsAdmin();
        apiBookingsSteps.acceptItemBooking();
    }

    @Test
    public void test041ApiUiValidationAcceptSupervisionedExtendedBooking() {
        apiLoginSteps.loginAsRegularUser();
        apiBookingsSteps.extendBooking(1, 1);
        loginFlowSteps.loginAsRegularUser();
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(false);
        apiLoginSteps.loginAsAdmin();
        apiBookingsSteps.acceptItemBooking();
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
    }
}
