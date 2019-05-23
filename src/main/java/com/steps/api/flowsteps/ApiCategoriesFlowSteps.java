package com.steps.api.flowsteps;

import com.steps.api.AbstractApiSteps;
import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class ApiCategoriesFlowSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;

    @StepGroup
    public void forcedDeleteAllCategories() {
        int initialNumberOfCategories;
        do {
            initialNumberOfCategories = apiCategorySteps.getTheNumberOfCategories();
            apiBookingsSteps.returnAllBookedItems();
            apiCategorySteps.deleteAllCategories();
        } while (initialNumberOfCategories > apiCategorySteps.getTheNumberOfCategories());
    }
}
