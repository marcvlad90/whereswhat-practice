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
public class Test003ApiUiValidationCRUDItem extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private ItemsFlowSteps itemsFlowSteps;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
    }

    @Test
    public void test003ApiUiValidationCRUDItem() {
        apiItemSteps.createItem();
        apiItemSteps.checkThatItemExists();
        itemsFlowSteps.checkIfItemIsPresentOrNot(true);
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiItemSteps.createItem();
        itemsFlowSteps.checkIfItemIsPresentOrNot(true);
        apiItemSteps.renameItem();
        itemsFlowSteps.checkIfItemIsPresentOrNot(true);
        itemsFlowSteps.checkIfItemsArePresentOrNot(true);
        apiItemSteps.deleteItem();
        itemsFlowSteps.checkIfItemIsPresentOrNot(false);
        apiItemSteps.deleteAllItemsFromCategory();
        apiItemSteps.deleteAllItemsFromCompany();
        itemsFlowSteps.checkIfItemsArePresentOrNot(false);
    }
}
