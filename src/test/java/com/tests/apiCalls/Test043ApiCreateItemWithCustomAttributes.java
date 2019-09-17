package com.tests.apiCalls;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.steps.api.ApiCategorySteps;
import com.steps.api.ApiItemSteps;
import com.steps.api.ApiLoginSteps;
import com.tests.BaseTest;

@RunWith(SerenityRunner.class)
public class Test043ApiCreateItemWithCustomAttributes extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemsSteps;

    @Before
    public void dataPrep() {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategoryWithCustomAttributes(2);
    }

    @Test
    public void test043ApiCreateItemWithCustomAttributes() {
        apiItemsSteps.createItemWithCustomFields();
        apiItemsSteps.checkThatItemExists();
    }
}
