package com.steps.frontend.flowsteps;

import java.util.List;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.AbstractSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ItemsSteps;
import com.tools.constants.Constants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Category;
import com.tools.entities.Item;
import com.tools.utils.CSVWriters;
import com.tools.utils.SerenitySessionUtils;

public class ItemsFlowSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    @Steps
    private HeaderSteps headerSteps;
    @Steps
    private ItemsSteps itemsSteps;

    @StepGroup
    public void importMultipleCategoriesItemsCsv(List<Item> items) throws Exception {
        CSVWriters.writeInCsv(items, Constants.DOWNLOAD_FILE_PATH + "Multiple Categories.csv");
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnAction(Constants.ITEMS_PAGE_ACTION_IMPORT_ITEMS);
        itemsSteps.uploadItemsCsv(Constants.DOWNLOAD_FILE_PATH + "Multiple Categories.csv");
        for (Item item : items) {
            SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, item);
        }
    }

    @StepGroup
    public void importDownloadedAndPopulatedMultipleCategoriesItemsCsv(List<Item> items, String fileName) throws Exception {
        CSVWriters.writeInCsv(items, Constants.DOWNLOAD_FILE_PATH + fileName);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnAction(Constants.ITEMS_PAGE_ACTION_IMPORT_ITEMS);
        itemsSteps.uploadItemsCsv(Constants.DOWNLOAD_FILE_PATH + fileName);
        for (Item item : items) {
            SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, item);
        }
    }

    @StepGroup
    public void downloadSingleCategoryItemsImportCsv() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnAction(Constants.ITEMS_PAGE_ACTION_IMPORT_ITEMS);
        itemsSteps.selectCategoryForImport(category.getName());
        itemsSteps.deleteTemplateIfExists(Constants.DOWNLOAD_FILE_PATH + category.getName() + ".csv");
        itemsSteps.clickOnDownloadCsvTemplate();
    }

    @StepGroup
    public void downloadMultipleCategoriesItemsImportCsv() {
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnAction(Constants.ITEMS_PAGE_ACTION_IMPORT_ITEMS);
        itemsSteps.deleteTemplateIfExists(Constants.DOWNLOAD_FILE_PATH + "Multiple Categories.csv");
        itemsSteps.clickOnDownloadCsvTemplate();
    }

    @StepGroup
    public void importSingleCategoryItemsCsv(List<Item> items) throws Exception {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        CSVWriters.writeInCsv(items, Constants.DOWNLOAD_FILE_PATH + category.getName() + ".csv");
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnAction(Constants.ITEMS_PAGE_ACTION_IMPORT_ITEMS);
        itemsSteps.selectCategoryForImport(category.getName());
        itemsSteps.uploadItemsCsv(Constants.DOWNLOAD_FILE_PATH + category.getName() + ".csv");
        for (Item item : items) {
            SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, item);
        }
    }

    @StepGroup
    public void checkIfCategoryIsPresentOrNot(boolean shouldBePresent) {
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.checkIfCategoryIsPresentOrNot(shouldBePresent);
    }

    @StepGroup
    public void checkIfCategoriesArePresentOrNot(boolean shouldBePresent) {
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.checkIfCategoriesArePresentOrNot(shouldBePresent);
    }

    @StepGroup
    public void checkIfItemIsPresentOrNot(boolean shouldBePresent) {
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchAndCheckIfItemIsPresentOrNot(shouldBePresent);
    }

    @StepGroup
    public void searchItemByTagAndCheckThatIsPresent(boolean shouldBePresent) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getItemTag());
        itemsSteps.checkIfCategoryOrItemIsPresentOrNot(shouldBePresent, item.getTitle());
    }

    @StepGroup
    public void checkIfItemsArePresentOrNot(boolean shouldBePresent) {
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchAndCheckIfItemsArePresentOrNot(shouldBePresent);
    }

    @StepGroup
    public void checkTheNumberOfClones(int expectedNumberOfClones) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.checkTheNumberOfItems(expectedNumberOfClones);
    }
}
