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
public class Test022ApiExtendBookingWithFailure extends BaseTest {
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
    }

    @Test
    public void test022ApiExtendBookingWithFailure() {
        apiItemSteps.createItem();
        apiBookingsSteps.bookItem(3, 3, 3, 3, 3, 3);
        apiBookingsSteps.bookItem(0, 0, 0, 1, 1, 1);
        apiBookingsSteps.extendBookingWithFailure(3, 3);
        apiBookingsSteps.checkIfBookingOfItemExists(false);
        apiItemSteps.createItem();
        apiBookingsSteps.bookItem(3, 3, 3, 3, 3, 3);
        apiBookingsSteps.bookItem(0, 0, 0, 1, 1, 1);
        apiBookingsSteps.extendBookingWithFailure(10, 10);
        apiBookingsSteps.checkIfBookingOfItemExists(false);
    }
}
