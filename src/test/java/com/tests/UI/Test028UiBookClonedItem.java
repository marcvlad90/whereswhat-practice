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
public class Test028UiBookClonedItem extends BaseTest {
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
    @Steps
    private ApiItemSteps apiItemsSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiItemSteps.cloneItem(1);
    }

    @Test
    public void test028UiBookClonedItem() {
        bookingsFlowSteps.bookItem(1, 1, 1, 1, 1, 1);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
        apiItemsSteps.deleteItem();
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(false);
        bookingsFlowSteps.bookItem(1, 1, 1, 1, 1, 1);
        bookingsFlowSteps.checkIfBookingIsPresentOrNotByCheckingDetails(true);
    }
}
