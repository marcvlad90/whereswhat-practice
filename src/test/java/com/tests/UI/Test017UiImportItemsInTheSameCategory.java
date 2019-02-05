package com.tests.UI;

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
public class Test017UiImportItemsInTheSameCategory extends BaseTest {
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
    @Steps
    private ApiItemSteps apiItemSteps;
    private List<Item> items;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        loginFlowSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        items = ItemFactory.getItemSameCategoryCSVInstanceList(3);

    }

    @Test
    public void test017UiImportItemsInTheSameCategory() throws Exception {
        itemsFlowSteps.importSingleCategoryItemsCsv(items);
        itemsFlowSteps.checkIfItemsArePresentOrNot(true);
    }
}
