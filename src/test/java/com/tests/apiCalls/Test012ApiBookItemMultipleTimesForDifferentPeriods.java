package com.tests.apiCalls;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.tests.BaseTest;

@RunWith(SerenityRunner.class)
public class Test012ApiBookItemMultipleTimesForDifferentPeriods extends BaseTest {
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
    }

    @Test
    public void test012ApiBookItemMultipleTimesForDifferentPeriods() {
        apiBookingsSteps.bookItem(1, 1, 1, 1, 1, 1);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiBookingsSteps.bookItem(5, 5, 5, 5, 5, 5);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiBookingsSteps.checkIfBookingsOfItemsExist(true);
        apiBookingsSteps.bookItem(100, 100, 100, 100, 100, 100);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiBookingsSteps.checkIfBookingsOfItemsExist(true);
    }
}
