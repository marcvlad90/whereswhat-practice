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
public class Test024UiBookItemMultipleTimesWithNoInterruption extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private BookingsFlowSteps bookingsFlowSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
    }

    @Test
    public void test024UiBookItemMultipleTimesWithNoInterruption() {
        bookingsFlowSteps.bookItem(1, 1, 7, 0, 0, 5);
        bookingsFlowSteps.bookItem(1, 1, 13, 0, 0, 5);
        bookingsFlowSteps.bookItem(1, 1, 19, 0, 0, 5);
        bookingsFlowSteps.checkIfBookingsArePresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingsArePresentOrNotInTheCalendar(true);
    }
}
