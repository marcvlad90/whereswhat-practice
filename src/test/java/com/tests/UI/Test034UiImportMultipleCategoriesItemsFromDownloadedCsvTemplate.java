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
public class Test034UiImportMultipleCategoriesItemsFromDownloadedCsvTemplate extends BaseTest {
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ItemsFlowSteps itemsFlowSteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    private List<Item> items;

    @Before
    public void dataPrep() {
        loginFlowSteps.loginAsAdmin();
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiCategorySteps.createCategory();
        itemsFlowSteps.downloadMultipleCategoriesItemsImportCsv();
        items = ItemFactory.getItemDifferentCategoriesCSVInstanceList(2);
    }

    @Test
    public void test034UiImportMultipleCategoriesItemsFromDownloadedCsvTemplate() throws Exception {
        itemsFlowSteps.importMultipleCategoriesItemsCsv(items);
        itemsFlowSteps.checkIfItemsArePresentOrNot(true);
    }
}
