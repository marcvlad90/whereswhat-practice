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
public class Test013UiBookItemMultipleTimesCommonPeriods extends BaseTest {
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
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiBookingSteps.bookItem(1, 1, 1, 1, 1, 1);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
    }

    @Test
    public void test013UiBookItemMultipleTimesCommonPeriods() {
        bookingsFlowSteps.bookItemWithFailure(1, 2, 2, 1, 1, 1);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(false);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(false);
        bookingsFlowSteps.bookItemWithFailure(0, 2, 2, 2, 2, 2);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(false);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(false);
    }
}
