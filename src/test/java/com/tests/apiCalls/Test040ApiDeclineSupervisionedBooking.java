package com.tests.apiCalls;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test040ApiDeclineSupervisionedBooking extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createSupervisionedCategory();
        apiItemSteps.createItem();
        apiLoginSteps.loginAsRegularUser();
    }

    @Test
    public void test040ApiDeclineSupervisionedBooking() {
        apiBookingsSteps.bookItem(1, 1, 1, 1, 1, 1);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiLoginSteps.loginAsAdmin();
        apiBookingsSteps.declineItemBooking();
        apiBookingsSteps.checkIfBookingOfItemExists(true);
    }
}
