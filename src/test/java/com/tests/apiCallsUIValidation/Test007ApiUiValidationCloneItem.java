package com.tests.apiCallsUIValidation;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.ItemsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test007ApiUiValidationCloneItem extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private ItemsFlowSteps itemsFlowTest;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
    }

    @Test
    public void test007ApiUiValidationCloneItem() {
        apiItemSteps.cloneItem(2);
        itemsFlowTest.checkTheNumberOfClones(3);
        apiItemSteps.cloneItem(3);
        itemsFlowTest.checkTheNumberOfClones(6);
        apiItemSteps.deleteItem();
        itemsFlowTest.checkTheNumberOfClones(5);
    }
}
