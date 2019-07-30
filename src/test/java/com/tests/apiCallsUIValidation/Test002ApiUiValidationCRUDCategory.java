package com.tests.apiCallsUIValidation;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.ItemsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test002ApiUiValidationCRUDCategory extends BaseTest {

    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private ItemsFlowSteps itemsFlowSteps;

    @Test
    public void test002ApiUiValidationCRUDCategory() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        itemsFlowSteps.checkIfCategoryIsPresentOrNot(true);
        apiCategorySteps.createCategory();
        apiCategorySteps.renameCategory();
        itemsFlowSteps.checkIfCategoriesArePresentOrNot(true);
        apiCategorySteps.deleteAllCategories();
        itemsFlowSteps.checkIfCategoriesArePresentOrNot(false);
    }
}
