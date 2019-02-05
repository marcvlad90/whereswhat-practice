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
public class Test037ApiMoveItemInAnotherCategory extends BaseTest {
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
        apiBookingsSteps.bookItem(1, 1, 1, 9, 0, 0);
    }

    @Test
    public void test037ApiMoveitemInAnotherCategory() {
        apiItemSteps.checkThatItemExists();
        apiBookingsSteps.checkIfBookingOfItemExists(true);
        apiCategorySteps.createCategory();
        apiCategorySteps.changeDefaultBookingLengthDaysValue(7);
        apiCategorySteps.changeMaxBookingLengthDaysValue(8);
        apiItemSteps.moveItemInAnotherCategory();
        apiItemSteps.checkThatItemExists();
        apiBookingsSteps.checkIfBookingOfItemExists(true);
    }
}
