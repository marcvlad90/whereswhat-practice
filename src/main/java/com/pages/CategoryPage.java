package com.pages;

import com.tools.constants.Constants;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;

public class CategoryPage extends AbstractPage {

    @FindBy(id = "category-name")
    private WebElement categoryNameField;
    @FindBy(id = "save-category")
    private WebElement addCategoryButton;
    @FindBy(id = "save-property-edit")
    private WebElement updateCategoryButton;
    @FindBy(css = "button[class*='upload-image-modal']")
    private WebElement selectImageToUploadButton;
    @FindBy(css = ".modal-footer .crop-n-save-image")
    private WebElement uploadImageOkButton;
    private final String spinnerElementCssSelector = ".spinner";

    public void uploadImageToCategory(String filePath) {
        selectImageToUploadButton.click();
        final File fNewTwo = new File(filePath);
        final String fileName = fNewTwo.getAbsolutePath();
        getDriver().findElement(By.cssSelector("#select-image")).sendKeys(fileName);
        uploadImageOkButton.click();
        clickUpdateCategoryButton();
    }

    public void insertCategoryName(String categoryName) {
        element(categoryNameField).clear();
        element(categoryNameField).sendKeys(categoryName);
    }

    public void clickAddCategoryButton() {
        addCategoryButton.click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        getDriver().navigate().refresh();
    }

    public void clickUpdateCategoryButton() {
        updateCategoryButton.click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        getDriver().navigate().refresh();
    }

}
