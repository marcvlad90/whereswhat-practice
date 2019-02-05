package com.tests.apiCallsUIValidation;

import com.steps.api.ApiLoginSteps;
import com.steps.api.ApiSettingSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.steps.frontend.flowsteps.SettingsFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test008ApiUiValidationCrudLocation extends BaseTest {
    @Steps
    private ApiSettingSteps apiSettingSteps;
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private SettingsFlowSteps settingsFlowSteps;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
    }

    @Test
    public void test008ApiUiValidationCrudLocation() {
        apiSettingSteps.createLocation();
        apiSettingSteps.checkThatLocationExists();
        settingsFlowSteps.checkIfLocationOrItemIsPresentOrNot(true);
        apiSettingSteps.deleteAllLocationsFromSession();
        settingsFlowSteps.checkIfLocationOrItemIsPresentOrNot(false);
        apiSettingSteps.createLocation();
        settingsFlowSteps.checkIfLocationOrItemIsPresentOrNot(true);
        apiSettingSteps.deleteLocation();
        settingsFlowSteps.checkIfLocationOrItemIsPresentOrNot(false);
        apiSettingSteps.createLocation();
        apiSettingSteps.deleteAllLocations();
    }
}
