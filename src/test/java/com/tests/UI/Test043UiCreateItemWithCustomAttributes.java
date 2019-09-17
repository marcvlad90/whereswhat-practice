package com.tests.UI;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.ItemFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

@RunWith(SerenityRunner.class)
public class Test043UiCreateItemWithCustomAttributes extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
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
    public void test043UiCreateItemWithCustomAttributes() {
        itemFlowSteps.createItemWithCustomFields();
        itemFlowSteps.checkItemCustomFieldValues();
    }
}
