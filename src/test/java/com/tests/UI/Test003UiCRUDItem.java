package com.tests.UI;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.ItemFlowSteps;
import com.steps.frontend.flowsteps.ItemsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test003UiCRUDItem extends BaseTest {
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
    @Steps
    private ItemFlowSteps itemFlowSteps;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
    }

    @Test
    public void test003UiCRUDItem() {
        itemFlowSteps.createItem();
        itemsFlowSteps.checkIfItemIsPresentOrNot(true);
        itemFlowSteps.renameItem();
        itemsFlowSteps.checkIfItemIsPresentOrNot(true);
        itemFlowSteps.deleteItem();
        itemsFlowSteps.checkIfItemIsPresentOrNot(false);
    }
}
