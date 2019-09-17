package com.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiLoginSteps;
import com.steps.api.flowsteps.ApiCategoriesFlowSteps;

@RunWith(SerenityRunner.class)
public class Test000CleanTheEnvironment extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategoriesFlowSteps apiCategoriesFlowSteps;

    @Test
    public void test000CleanTheEnvironment() {
        apiLoginSteps.loginAsAdmin();
        apiCategoriesFlowSteps.forcedDeleteAllCategories(100);
    }
}
