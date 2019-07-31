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
public class Test042ApiDeclineSupervisionedExtendedBooking extends BaseTest {
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
        apiBookingsSteps.bookItemFromNow(1, 1, 1);
        apiLoginSteps.loginAsAdmin();
        apiBookingsSteps.acceptItemBooking();
    }

    @Test
    public void test042ApiDeclineSupervisionedExtendedBooking() {
        apiLoginSteps.loginAsRegularUser();
        apiBookingsSteps.extendBooking(1, 1);
        apiBookingsSteps.checkIfBookingOfItemExists(false);
        apiLoginSteps.loginAsAdmin();
        apiBookingsSteps.declineItemBookingExtension();
        apiBookingsSteps.checkIfBookingOfItemExists(true);
    }
}
