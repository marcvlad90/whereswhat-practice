package com.tests.UI;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.ItemsSteps;
import com.steps.frontend.flowsteps.ItemsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test036UiSearchItemByTag extends BaseTest {
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ItemsFlowSteps itemsFlowSteps;
    @Steps
    private ItemsSteps itemsSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
    }

    @Test
    public void test036UiSearchItemByTag() {
        itemsFlowSteps.searchItemByTagAndCheckThatIsPresent(true);
        apiItemSteps.deleteItem();
        itemsFlowSteps.searchItemByTagAndCheckThatIsPresent(false);
    }
}
