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
public class Test007ApiCloneItem extends BaseTest {
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
        apiItemSteps.createItem();
    }

    @Test
    public void test007ApiCloneItem() {
        apiItemSteps.cloneItem(25);
        apiItemSteps.checkTheNumberOfClones(26);
        apiItemSteps.cloneItem(10);
        apiItemSteps.checkTheNumberOfClones(36);
        apiItemSteps.deleteItem();
        apiItemSteps.checkTheNumberOfClones(35);
    }
}
