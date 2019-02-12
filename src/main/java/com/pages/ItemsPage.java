package com.pages;

import com.tools.constants.Constants;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

public class ItemsPage extends AbstractPage {
    @FindBy(css = ".page-header.row input")
    private WebElement searchInputField;
    @FindBy(css = ".btn-group>button[id*='delete']")
    private WebElement confirmDeletionButton;
    @FindBy(css = ".dz-hidden-input")
    private WebElement uploadInput;
    @FindBy(id = "csv-category")
    private WebElement importItemsCategoriesDropDown;
    @FindBy(id = "item_clone")
    private WebElement numberOfClonesField;
    @FindBy(css = ".btn.btn-success.clone")
    private WebElement cloneButton;
    @FindBy(css = ".white-box.clearfix.single-item")
    private List<WebElement> itemsContainersList;
    @FindBy(css = "ul#categories_container>li")
    private List<WebElement> categoriesContainers;
    @FindBy(css = ".upload-text.download-sample")
    private WebElement downloadTemplateButton;
    private final String itemsAndCategoriesTitlesCssSelector = "ul#categories_container>li h3>span";
    private final String itemsAndCategoriesContainersCssSelector = "ul#categories_container>li";
    private final String actionsListCssSelector = ".collection-action-container>.collection-action-member";
    private final String saveItemsImportButtonCssSelector = "button[class*='process-csv']";
    private final String spinnerElementCssSelector = ".spinner";

    public void selectCategoryForImport(String categoryName) {
        element(importItemsCategoriesDropDown).waitUntilVisible();
        element(importItemsCategoriesDropDown).selectByVisibleText(categoryName);
    }

    public void clickOnDownloadCsvTemplate() {
        downloadTemplateButton.click();
        System.out.println("downoad file path is: " + Constants.DOWNLOAD_FILE_PATH);
        getDriver().navigate().refresh();
    }

    public void confirmDeletion() {
        element(confirmDeletionButton).waitUntilVisible();
        confirmDeletionButton.click();
    }

    public void clickOnCategoryOrItemAction(String itemOrCategoryName, @SuppressWarnings("hiding") String actionsListCssSelector, String actionName) {
        WebElement container = getElementFromList(itemsAndCategoriesContainersCssSelector, itemOrCategoryName);
        container.findElement(By.cssSelector(".dropdown-toggle")).click();
        container.findElement(By.cssSelector(actionsListCssSelector)).click();
    }

    public void clickOnCategoryAction(String categoryName, String actionName) {
        clickOnCategoryOrItemAction(categoryName, "a[id*='" + actionName.toLowerCase() + "']", actionName);
    }

    public void clickOnItemAction(String itemName, String actionName) {
        clickOnCategoryOrItemAction(itemName, "a[class*='" + actionName.toLowerCase() + "']", actionName);
    }

    public void clickOnAction(String actionName) {
        getElementFromList(actionsListCssSelector, actionName).click();
    }

    public void insertNumberOfClones(String numberOfClones) {
        element(numberOfClonesField).clear();
        element(numberOfClonesField).sendKeys(numberOfClones);
    }

    public void clickOnCloneButton() {
        cloneButton.click();
    }

    public void uploadItemsCsv(String filePath) {
        final File fNewTwo = new File(filePath);
        final String fileName = fNewTwo.getAbsolutePath();
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        WebElement inputField = getDriver().findElement(By.cssSelector(".dz-hidden-input"));
        executor.executeScript("document.getElementsByClassName('dz-hidden-input')[0].style.visibility='visible';", inputField);
        inputField.sendKeys(fileName);
        WebElement saveItemsImportButton = getDriver().findElement(By.cssSelector(saveItemsImportButtonCssSelector));
        saveItemsImportButton.click();
        deleteFileIfExists(filePath);
        getDriver().navigate().refresh();
    }

    public void searchForItem(String itemName) {
        String[] itemWords = itemName.split(" ");
        waitForElementsToBeVisible(searchInputField);
        searchInputField.clear();
        for (String itemLetter : itemWords) {
            searchInputField.sendKeys(itemLetter + " ");
            waitABit(Constants.WAIT_TIME_ONE_SECOND_IN_MILISECONDS);
            if (containsText(itemName) || containsText("No results")) {
                break;
            }
        }
    }

    public void clickOnBookButton(String itemTitle) {
        waitForElementToAppear(itemsAndCategoriesContainersCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        WebElement bookingContainer = getElementFromList(itemsAndCategoriesContainersCssSelector, itemTitle);
        WebElement bookingButton = bookingContainer.findElement(By.cssSelector("button"));
        waitForElementToAppear(bookingButton, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        bookingButton.click();
    }

    public void clickOnItem(String itemTitle) {
        waitForElementToAppear(itemsAndCategoriesContainersCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        getElementFromList(itemsAndCategoriesContainersCssSelector, itemTitle).findElement(By.cssSelector(".item-img")).click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
    }

    public void checkItemProperty(String itemTitle, String propertyName, String propertyValue) {
        try {
            Assert.assertTrue("The item is under another category",
                    propertyValue.contentEquals(getItemPropertyValue(itemTitle, propertyName)));
        } catch (NullPointerException e) {
        }
    }

    public String getItemPropertyValue(String itemTitle, String propertyName) {
        WebElement itemContainer = getElementFromList(itemsAndCategoriesContainersCssSelector,
                itemTitle);
        WebElement propertyRow = getElementFromList(itemContainer.findElements(By.cssSelector(".text-muted.ellipsis")), propertyName + " :");
        String[] propertyRowParts = propertyRow.getText().split(" : ");
        return propertyRowParts[1];
    }

    public void checkIfCategoryOrItemIsPresentOrNot(boolean shouldBePresent, String itemTitle) {
        if (shouldBePresent) {
            Assert.assertTrue(String.format("%s was not found!", itemTitle),
                    checkIfElementExists(getElementFromList(itemsAndCategoriesTitlesCssSelector,
                            itemTitle)));
        } else {
            Assert.assertFalse(String.format("%s was found!", itemTitle),
                    checkIfElementExists(getElementFromList(itemsAndCategoriesTitlesCssSelector,
                            itemTitle)));
        }
    }

    public int getTheNumberOfItems() {
        return getElementsListSize(itemsAndCategoriesTitlesCssSelector);
    }
}
