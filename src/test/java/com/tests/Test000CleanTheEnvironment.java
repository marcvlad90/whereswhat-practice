package com.tests;

import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiLoginSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test000CleanTheEnvironment extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;

    @Test
    public void test000CleanTheEnvironment() {
        apiLoginSteps.loginAsAdmin();
        while (apiCategorySteps.getTheNumberOfCategories() > 0) {
            apiBookingsSteps.returnAllBookedItems();
            apiCategorySteps.deleteAllCategories();
        }
    }
}
