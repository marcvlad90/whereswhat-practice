package com.tests.apiCallsUIValidation;

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
public class Test027ApiUiValidationCloneBookedItem extends BaseTest {
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
        apiBookingsSteps.bookItem(1, 1, 1, 1, 1, 1);
    }

    @Test
    public void test027ApiUiValidationCloneBookedItem() {
        apiItemSteps.cloneItem(3);
        apiBookingsSteps.checkIfBookingsOfItemsExist(true);
        apiBookingsSteps.deleteItemBooking();
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(false);
    }
}
