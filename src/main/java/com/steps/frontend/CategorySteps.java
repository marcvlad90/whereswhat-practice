package com.steps.frontend;

import net.thucydides.core.annotations.Step;

import com.pages.CategoryPage;

public class CategorySteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private CategoryPage categoryPage;

    @Step
    public void uploadImageToCategory(String filePath) {
        categoryPage.uploadImageToCategory(filePath);
    }

    @Step
    public void insertCategoryName(String categoryName) {
        categoryPage.insertCategoryName(categoryName);
    }

    @Step
    public void clickAddCategoryButton() {
        categoryPage.clickAddCategoryButton();
    }

    @Step
    public void clickUpdateCategoryButton() {
        categoryPage.clickUpdateCategoryButton();
    }
}
