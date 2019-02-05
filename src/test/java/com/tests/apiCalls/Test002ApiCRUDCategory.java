package com.tests.apiCalls;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiLoginSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test002ApiCRUDCategory extends BaseTest {

    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;

    @Test
    public void test002ApiCRUDCategory() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        apiCategorySteps.checkThatCategoryExists();
        apiCategorySteps.createCategory();
        apiCategorySteps.renameCategory();
        apiCategorySteps.checkThatCategoryExists();
        apiCategorySteps.checkThatCategoriesExist();
        apiCategorySteps.deleteAllCategoriesFromSession();
        apiCategorySteps.deleteAllCategories();
    }
}
