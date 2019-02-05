package com.tests.apiCallsUIValidation;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.steps.frontend.flowsteps.ItemsFlowSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;
import com.tools.entities.Item;
import com.tools.factories.ItemFactory;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(SerenityRunner.class)
public class Test018ApiUiValidationImportItemsInDifferentCategories extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemsSteps;
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private ItemsFlowSteps itemsFlowSteps;
    private List<Item> items;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiCategorySteps.createCategory();
        apiCategorySteps.createCategory();
        items = ItemFactory.getItemDifferentCategoriesCSVInstanceList(3);
    }

    @Test
    public void test018ApiUiValidationImportItemsInDifferentCategories() throws Exception {
        apiItemsSteps.createMultipleItemsFromCsvTemplate(items);
        itemsFlowSteps.checkIfItemsArePresentOrNot(true);
    }
}
