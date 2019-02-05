package com.tests.apiCalls;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
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
public class Test009ApiLocateItem extends BaseTest {
    @Managed(uniqueSession = true)
    WebDriver webdriver;
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiSettingSteps apiSettingSteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private ApiCategorySteps apiCategoriesSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiSettingSteps.createLocation();
        apiItemSteps.createItem();
    }

    @Test
    public void test009ApiLocateItem() {
        apiItemSteps.locateItem();
        apiItemSteps.checkItemLocation();
    }

    @Override
    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        apiBookingsSteps.returnAllBookedItemsFromSession();
        apiCategoriesSteps.deleteAllCategoriesFromSession();
        apiSettingSteps.deleteAllLocationsFromSession();
        webdriver.quit();
    }
}
