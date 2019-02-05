package com.tests.UI;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.CategoryFlowSteps;
import com.steps.frontend.flowsteps.ItemFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test010UiUploadImageForCategoryAndItem extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ItemFlowSteps itemFlowSteps;
    @Steps
    private CategoryFlowSteps categoryFlowSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
    }

    @Test
    public void test010UiUploadImageForCategoryAndItem() {
        categoryFlowSteps.uploadImageToCategory("category.png");
        itemFlowSteps.uploadImageToItem("item.png");
    }
}
