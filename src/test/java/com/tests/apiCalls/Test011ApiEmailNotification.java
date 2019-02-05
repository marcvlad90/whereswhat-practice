package com.tests.apiCalls;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiEmailSteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.api.ApiSettingSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test011ApiEmailNotification extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiSettingSteps apiSettingsSteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiEmailSteps apiEmailSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createSupervisionedCategory();
        apiItemSteps.createItem();
        apiLoginSteps.loginAsRegularUser();
    }

    @Test
    public void test011ApiEmailNotification() {
        apiBookingsSteps.bookItem(1, 1, 1, 1, 1, 1);
        apiEmailSteps.checkThatBookingRequestEmailNotificationWasReceived();

    }
}
