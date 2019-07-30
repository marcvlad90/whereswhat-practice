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
public class Test005UiReturnItem extends BaseTest {
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
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiBookingSteps.bookItemFromNow(1, 1, 1);
    }

    @Test
    public void test005UiReturnItem() {
        bookingFlowSteps.returnBookedItemViaCalendar();
        bookingFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingFlowSteps.checkIfBookingIsPresentOrNotInTheCalendar(true);
    }
}
