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
public class Test003ApiCRUDItem extends BaseTest {
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
    }

    @Test
    public void test003ApiCRUDItem() {
        apiItemSteps.createItem();
        apiItemSteps.checkThatItemExists();
        apiCategorySteps.createCategory();
        apiItemSteps.createItem();
        apiItemSteps.createItem();
        apiItemSteps.checkThatItemExists();
        apiItemSteps.checkThatItemsExist();
        apiItemSteps.renameItem();
        apiItemSteps.checkThatItemExists();
        apiItemSteps.checkThatItemsExist();
        apiItemSteps.deleteItem();
        apiItemSteps.deleteAllItemsFromCategory();
        apiItemSteps.deleteAllItemsFromCurrentSession();
    }
}
