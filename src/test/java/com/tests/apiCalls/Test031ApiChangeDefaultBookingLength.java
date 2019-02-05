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
public class Test031ApiChangeDefaultBookingLength extends BaseTest {
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
    public void test031ApiChangeDefaultBookingLength() {
        apiCategorySteps.changeDefaultBookingLengthDaysValue(3);
        apiBookingsSteps.bookItemWithDefaultSetLength();
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiCategorySteps.changeDefaultBookingLengthDaysValue(10);
        apiItemSteps.createItem();
        apiBookingsSteps.bookItemWithDefaultSetLength();
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiCategorySteps.changeDefaultBookingLengthHoursValue(30);
        apiItemSteps.createItem();
        apiBookingsSteps.bookItemWithDefaultSetLength();
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiCategorySteps.changeDefaultBookingLengthHoursValue(100);
        apiItemSteps.createItem();
        apiBookingsSteps.bookItemWithDefaultSetLength();
        apiBookingsSteps.checkIfBookingOfItemExists(true);
    }
}
