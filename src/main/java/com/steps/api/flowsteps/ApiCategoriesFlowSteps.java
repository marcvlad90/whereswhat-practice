package com.steps.api.flowsteps;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.api.AbstractApiSteps;
import com.steps.api.ApiBookingsSteps;
import com.steps.api.ApiCategorySteps;

public class ApiCategoriesFlowSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;
    @Steps
    private ApiBookingsSteps apiBookingsSteps;
    @Steps
    private ApiCategorySteps apiCategorySteps;

    @StepGroup
    public void forcedDeleteAllCategories(int numberOfAttempts) {
        int initialNumberOfCategories;
        int i = 0;
        do {
            initialNumberOfCategories = apiCategorySteps.getTheNumberOfCategories();
            apiBookingsSteps.returnAllBookedItemsFromCompany();
            apiCategorySteps.deleteAllCategoriesFromCompany();
            i++;
        } while ((initialNumberOfCategories > apiCategorySteps.getTheNumberOfCategories()) && (i < numberOfAttempts));
    }
}
