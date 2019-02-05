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
public class Test006ApiExtendBooking extends BaseTest {
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
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiBookingsSteps.bookItem(0, 0, 0, 1, 1, 1);
    }

    @Test
    public void test006ApiExtendBooking() {
        apiBookingsSteps.extendBooking(1, 1);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiBookingsSteps.extendBooking(10, 10);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiBookingsSteps.extendBooking(100, 100);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiBookingsSteps.extendBooking(-90, -90);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
    }
}
