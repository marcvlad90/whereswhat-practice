package com.tests.UI;

import com.steps.frontend.flowsteps.CategoryFlowSteps;
import com.steps.frontend.flowsteps.ItemsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test002UiCRUDCategory extends BaseTest {
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private ItemsFlowSteps itemsFlowSteps;
    @Steps
    private CategoryFlowSteps categoryFlowSteps;

    @Test
    public void test002UiCRUDCategory() {
        loginFlowSteps.loginAsAdmin();
        categoryFlowSteps.createCategory();
        itemsFlowSteps.checkIfCategoryIsPresentOrNot(true);
        categoryFlowSteps.renameCategory();
        itemsFlowSteps.checkIfCategoryIsPresentOrNot(true);
        categoryFlowSteps.deleteCategory();
        itemsFlowSteps.checkIfCategoryIsPresentOrNot(false);
    }
}
