package com.tests.apiCalls;

import com.steps.api.ApiLoginSteps;
import com.steps.api.ApiSettingSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test008ApiCrudLocation extends BaseTest {
    @Steps
    private ApiSettingSteps apiSettingSteps;
    @Steps
    private ApiLoginSteps apiLoginSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
    }

    @Test
    public void test008ApiCrudLocation() {
        apiSettingSteps.createLocation();
        apiSettingSteps.checkThatLocationExists();
        apiSettingSteps.deleteAllLocationsFromSession();
        apiSettingSteps.createLocation();
        apiSettingSteps.deleteLocation();
        apiSettingSteps.createLocation();
        apiSettingSteps.deleteAllLocations();
    }
}
