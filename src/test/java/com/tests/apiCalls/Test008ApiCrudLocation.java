package com.tests.apiCalls;

import com.steps.api.ApiLoginSteps;
import com.steps.api.ApiSettingSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class Test008ApiCrudLocation extends BaseTest {
    @Managed(uniqueSession = true)
    WebDriver webdriver;
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

    }

    @Override
    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        apiLoginSteps.loginAsAdmin();
        apiSettingSteps.deleteAllLocations();
        webdriver.quit();
    }
}
