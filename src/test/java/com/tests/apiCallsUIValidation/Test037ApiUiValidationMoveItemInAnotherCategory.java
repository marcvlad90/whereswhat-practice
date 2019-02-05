package com.tests.apiCallsUIValidation;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.BookingsFlowSteps;
import com.steps.frontend.flowsteps.ItemFlowSteps;
import com.steps.frontend.flowsteps.ItemsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test037ApiUiValidationMoveItemInAnotherCategory extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private ItemsFlowSteps itemsFlowSteps;
    @Steps
    private ItemFlowSteps itemFlowSteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private BookingsFlowSteps bookingsFlowSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiBookingsSteps.bookItem(1, 1, 1, 9, 0, 0);
    }

    @Test
    public void test037ApiUiValidationMoveItemInAnotherCategory() {
        itemsFlowSteps.checkIfItemIsPresentOrNot(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingsArePresentOrNotInTheCalendar(true);
        apiCategorySteps.createCategory();
        apiCategorySteps.changeDefaultBookingLengthDaysValue(7);
        apiCategorySteps.changeMaxBookingLengthDaysValue(8);
        itemFlowSteps.moveItemInAnotherCategory();
        itemsFlowSteps.checkIfItemIsPresentOrNot(true);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        bookingsFlowSteps.checkIfBookingsArePresentOrNotInTheCalendar(true);
    }
}
