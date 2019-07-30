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
public class Test005ApiReturnItem extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiBookingsSteps apiBookingSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiBookingSteps.bookItemFromNow(1, 1, 1);
    }

    @Test
    public void test005ApiReturnItem() {
        apiBookingSteps.returnBookedItem();
        apiBookingSteps.checkThatBookedItemWasReturned();
        apiBookingSteps.returnAllBookedItemsFromSession();
        apiBookingSteps.checkThatAllBookedItemsWereReturned();
    }
}
