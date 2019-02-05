package com.tests.UI;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiEmailSteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.api.ApiSettingSteps;
import com.steps.frontend.flowsteps.BookingsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test011UiEmailNotification extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiSettingSteps apiSettingsSteps;
    @Steps
    private BookingsFlowSteps bookingsFlowSteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiEmailSteps apiEmailSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiSettingsSteps.enableBookingRequestsNotifications();
        apiCategorySteps.createSupervisionedCategory();
        apiItemSteps.createItem();
        loginFlowSteps.loginAsRegularUser();
    }

    @Test
    public void test011UiEmailNotification() {
        bookingsFlowSteps.bookItem(1, 1, 1, 1, 1, 1);
        apiEmailSteps.checkThatBookingRequestEmailNotificationWasReceived();
    }
}
