package com.tests.apiCallsUIValidation;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.ItemFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

@RunWith(SerenityRunner.class)
public class Test043ApiUiValidationCreateItemWithCustomAttributes extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemsSteps;
    @Steps
    private ItemFlowSteps itemFlowSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategoryWithCustomAttributes(3);
    }

    @Test
    public void test043ApiUiValidationCreateItemWithCustomAttributes() {
        apiItemsSteps.createItemWithCustomFields();
        itemFlowSteps.checkItemCustomFieldValues();
    }
}
