package com.pages;

import java.io.File;
import java.util.List;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.tools.constants.Constants;

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
    private final String itemsAndCategoriesContainersXpathSelector = "//ul[@id='categories_container']/li";
    private final String actionsListCssSelector = ".collection-action-container>.collection-action-member";
    private final String saveItemsImportButtonCssSelector = "button[class*='process-csv']";
    private final String spinnerElementCssSelector = ".spinner";

    public void selectCategoryForImport(String categoryName) {
        waitForElementToAppear(importItemsCategoriesDropDown, Constants.WAIT_TIME_MAXIMUM_IN_MILISECONDS);
        element(importItemsCategoriesDropDown).selectByVisibleText(categoryName);
    }

    public void clickOnDownloadCsvTemplate() {
        waitForElementToBeClickable(downloadTemplateButton, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        downloadTemplateButton.click();
        getDriver().navigate().refresh();
    }

    public void confirmDeletion() {
        element(confirmDeletionButton).waitUntilVisible();
        confirmDeletionButton.click();
        getDriver().navigate().refresh();
    }

    public void loadAllCategoriesList() {
        getDriver().navigate().refresh();
        int categoriesContainersSize;
        int i = 0;
        do {
            List<WebElement> categoriesContainers = getDriver().findElements(By.xpath(itemsAndCategoriesContainersXpathSelector));
            categoriesContainersSize = categoriesContainers.size();
            if ((categoriesContainersSize % 10) == 0) {
                for (int j = i; j < categoriesContainers.size(); j++) {
                    clickOn(categoriesContainers.get(j));
                }
                i = i + 10;
            }
        } while (categoriesContainersSize < getDriver().findElements(By.xpath(itemsAndCategoriesContainersXpathSelector)).size());
    }

    public void clickOnCategoryOrItemAction(String itemOrCategoryName, String actionsListCssSelector, String actionName) {
        waitForTextToAppear(itemOrCategoryName);
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
        loadAllCategoriesList();
        waitForTextToAppear(actionName);
        WebElement actionItem = getElementFromList(actionsListCssSelector, actionName);
        clickOnElementUsingJavascript(actionItem);
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
        int numberOfTries = 0;
        boolean isSearchCompleted = false;
        do {
            try {
                numberOfTries++;
                String[] itemWords = itemName.split(" ");
                waitForElementToAppear(searchInputField, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
                searchInputField.clear();
                for (String itemLetter : itemWords) {
                    searchInputField.sendKeys(itemLetter + " ");
                    waitABit(Constants.WAIT_TIME_ONE_SECOND_IN_MILISECONDS);
                    if (containsText(itemName) || containsText("No results")) {
                        break;
                    }
                }
                isSearchCompleted = true;
            } catch (WebDriverException e) {
                e.getMessage();
            }
        } while ((numberOfTries < 3) && !isSearchCompleted);

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
            e.printStackTrace();
        }
    }

    public String getItemPropertyValue(String itemTitle, String propertyName) {
        WebElement itemContainer = getElementFromList(itemsAndCategoriesContainersCssSelector,
                itemTitle);
        WebElement propertyRow = getElementFromList(itemContainer.findElements(By.cssSelector(".text-muted.ellipsis")), propertyName + " :");
        String[] propertyRowParts = propertyRow.getText().split(" : ");
        return propertyRowParts[1];
    }

    public void checkIfCategoryOrItemIsPresentOrNot(boolean shouldBePresent, String title) {
        boolean staleElementException = false;
        do {
            try {
                if (shouldBePresent) {
                    Assert.assertTrue(String.format("%s was not found!", title),
                            checkIfElementExists(getElementFromList(itemsAndCategoriesTitlesCssSelector,
                                    title)));
                    staleElementException = false;
                } else {
                    Assert.assertFalse(String.format("%s was found!", title),
                            checkIfElementExists(getElementFromList(itemsAndCategoriesTitlesCssSelector,
                                    title)));
                    staleElementException = false;
                }
            } catch (StaleElementReferenceException e2) {
                staleElementException = true;
            }
        } while (staleElementException);

    }

    public int getTheNumberOfItems() {
        return getElementsListSize(itemsAndCategoriesTitlesCssSelector);
    }
}
