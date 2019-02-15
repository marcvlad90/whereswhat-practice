package com.tests;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiLoginSteps;
import com.steps.api.flowsteps.ApiCategoriesFlowSteps;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.StepEventBus;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    @Managed(uniqueSession = true)
    WebDriver webdriver;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private ApiCategorySteps apiCategoriesSteps;
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategoriesFlowSteps apiCategoriesFlowSteps;

    @Before
    public void setup() {
        //        System.setProperty("http.proxyHost", "localhost");
        //        System.setProperty("http.proxyPort", "8080");
        //        System.setProperty("https.proxyHost", "localhost");
        //        System.setProperty("https.proxyPort", "8080");
    }

    @After
    public void tearDown() {
        StepEventBus.getEventBus().clearStepFailures();
        apiLoginSteps.loginAsAdmin();
        apiCategoriesFlowSteps.forcedDeleteAllCategories();
        webdriver.quit();
    }
}
