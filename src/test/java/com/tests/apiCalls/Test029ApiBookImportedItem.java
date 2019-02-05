package com.tests.apiCalls;

import com.steps.api.ApiBookingsSteps;
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
public class Test029ApiBookImportedItem extends BaseTest {
    @Steps
    private ApiLoginSteps apiLoginSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;
    @Steps
    private ApiItemSteps apiItemSteps;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;

    private List<Item> items;

    @Before
    public void dataPrep() throws Exception {
        apiLoginSteps.loginAsAdmin();
        apiCategorySteps.createCategory();
        items = ItemFactory.getItemSameCategoryCSVInstanceList(1);
        apiItemSteps.createMultipleItemsFromCsvTemplate(items);
    }

    @Test
    public void test029ApiBookImportedItem() {
        apiBookingsSteps.bookItem(1, 1, 1, 1, 1, 1);
        apiBookingsSteps.checkIfBookingOfItemExists(true);
    }
}
