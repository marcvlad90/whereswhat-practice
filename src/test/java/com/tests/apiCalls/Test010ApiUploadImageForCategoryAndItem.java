package com.tests.apiCalls;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test010ApiUploadImageForCategoryAndItem extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiItemSteps.createItem();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
    }

    @Test
    public void test010ApiUploadImageForCategoryAndItem() {
        apiCategorySteps.uploadImageToCategory("category.png");
        apiItemSteps.uploadImageToItem("item.png");
        apiCategorySteps.uploadImageToCategoriesFromSession("category.png");
        apiItemSteps.uploadImageToAllItemsFromSession("item.png");
    }
}
