package com.tests.UI;

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
public class Test040UiDeclineSupervisionedBooking extends BaseTest {
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
    public void test040UiDeclineSupervisionedBooking() {
        apiBookingsSteps.bookItem(1, 1, 1, 1, 1, 1);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        headerSteps.logout();
        loginFlowSteps.loginAsAdmin();
        bookingsFlowSteps.declineItemBooking();
        headerSteps.logout();
        loginFlowSteps.loginAsRegularUser();
        bookingsFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(false);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
    }
}
