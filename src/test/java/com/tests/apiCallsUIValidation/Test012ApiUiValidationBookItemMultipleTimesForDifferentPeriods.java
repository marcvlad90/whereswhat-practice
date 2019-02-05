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
public class Test012ApiUiValidationBookItemMultipleTimesForDifferentPeriods extends BaseTest {
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
    private BookingsFlowSteps bookingsFlowSteps;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
    }

    @Test
    public void test012ApiUiValidationBookItemMultipleTimesForDifferentPeriods() {
        apiBookingSteps.bookItem(1, 1, 1, 1, 1, 1);
        apiBookingSteps.bookItem(5, 5, 5, 5, 5, 5);
        apiBookingSteps.bookItem(100, 100, 100, 100, 100, 100);
        bookingsFlowSteps.checkIfBookingsArePresentOrNotInTheCalendar(true);
        bookingsFlowSteps.checkIfBookingsArePresentOrNotByCheckingDetails(true);
    }
}
