package com.steps.frontend;

import java.util.List;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;

import org.junit.Assert;

import com.pages.ItemsPage;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Category;
import com.tools.entities.Item;
import com.tools.utils.SerenitySessionUtils;

public class ItemsSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private ItemsPage itemsPage;

    @Step
    public void confirmDeletion() {
        itemsPage.confirmDeletion();
    }

    @Step
    public void deleteTemplateIfExists(String filePath) {
        itemsPage.deleteFileIfExists(filePath);
    }

    @Step
    public void selectCategoryForImport(String categoryName) {
        itemsPage.selectCategoryForImport(categoryName);
    }

    @Step
    public void clickOnDownloadCsvTemplate() {
        itemsPage.clickOnDownloadCsvTemplate();
    }

    @Step
    public void clickOnCategoryAction(String categoryName, String actionName) {
        itemsPage.loadAllCategoriesList();
        itemsPage.clickOnCategoryAction(categoryName, actionName);
    }

    @Step
    public void clickOnItemAction(String itemName, String actionName) {
        itemsPage.clickOnItemAction(itemName, actionName);
    }

    @Step
    public void clickOnBookButton(String itemTitle) {
        itemsPage.clickOnBookButton(itemTitle);
    }

    @Step
    public void clickOnItem(String itemTitle) {
        itemsPage.clickOnItem(itemTitle);
    }

    @Step
    public void uploadItemsCsv(String filePath) {
        itemsPage.uploadItemsCsv(filePath);
    }

    @Step
    public void insertNumberOfClones(String numberOfClones) {
        itemsPage.insertNumberOfClones(numberOfClones);
    }

    @Step
    public void clickOnCloneButton() {
        itemsPage.clickOnCloneButton();
    }

    @Step
    public void clickOnAction(String actionName) {
        itemsPage.clickOnAction(actionName);
    }

    @Step
    public void checkIfCategoryIsPresentOrNot(boolean shouldBePresent) {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        itemsPage.loadAllCategoriesList();
        itemsPage.checkIfCategoryOrItemIsPresentOrNot(shouldBePresent, category.getName());
    }

    @Step
    public void checkIfCategoriesArePresentOrNot(boolean shouldBePresent) {
        List<Category> categories = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
        for (Category category : categories) {
            itemsPage.loadAllCategoriesList();
            itemsPage.checkIfCategoryOrItemIsPresentOrNot(shouldBePresent, category.getName());
        }
    }

    @Step
    public void checkItemProperty(String itemTitle, String propertyName, String propertyValue) {
        itemsPage.checkItemProperty(itemTitle, propertyName, propertyValue);
    }

    @Step
    public void checkIfCategoryOrItemIsPresentOrNot(boolean shouldBePresent, String itemTitle) {
        itemsPage.checkIfCategoryOrItemIsPresentOrNot(shouldBePresent, itemTitle);
    }

    @StepGroup
    public void searchAndCheckIfItemIsPresentOrNot(boolean shouldBePresent) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        searchForItem(item.getTitle());
        checkIfCategoryOrItemIsPresentOrNot(shouldBePresent, item.getTitle());
        checkItemProperty(item.getTitle(), "Category", category.getName());

    }

    @StepGroup
    public void searchAndCheckIfItemsArePresentOrNot(boolean shouldBePresent) {
        List<Item> items = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
        for (Item item : items) {
            searchForItem(item.getTitle());
            checkIfCategoryOrItemIsPresentOrNot(shouldBePresent, item.getTitle());
        }
    }

    @Step
    public void searchForItem(String itemName) {
        itemsPage.searchForItem(itemName);
    }

    @Step
    public void checkTheNumberOfItems(int expectedNumberOfClones) {
        Assert.assertTrue("The actual number of clones is " + itemsPage.getTheNumberOfItems() + " instead of "
                + expectedNumberOfClones,
                itemsPage.getTheNumberOfItems() == expectedNumberOfClones);
    }

}
