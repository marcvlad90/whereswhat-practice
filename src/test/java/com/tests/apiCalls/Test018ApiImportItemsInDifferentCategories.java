package com.tests.apiCalls;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
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
public class Test018ApiImportItemsInDifferentCategories extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemsSteps;
    private List<Item> items;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiCategorySteps.createCategory();
        apiCategorySteps.createCategory();
        items = ItemFactory.getItemDifferentCategoriesCSVInstanceList(3);
    }

    @Test
    public void test018ApiImportItemsInTheSameCategory() throws Exception {
        apiItemsSteps.createMultipleItemsFromCsvTemplate(items);
        apiItemsSteps.checkThatItemsExist();
    }
}
