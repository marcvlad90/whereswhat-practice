package com.pages;

import com.tools.constants.Constants;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HeaderPage extends AbstractPage {
    @FindBy(id = "log-out")
    private WebElement confirmLogoutButton;
    private String menuItemsCssSelector = "#menu a[href='#textToReplace()']";
    private final String profileDropDownMenuItemsListCssSelector = ".dropdown.navbar-right.open .dropdown-menu>li";
    private final String spinnerElementCssSelector = ".spinner";
    private final String profileDropDownCssSelector = ".dropdown-toggle";

    public void navigateToMenu(String menu) {
        waitForTextToAppear(menu, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        WebElement menuItem = getDriver().findElement(By.cssSelector(menuItemsCssSelector.replace("textToReplace()", menu.toLowerCase())));
        waitForElementToBeClickable(menuItem, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        clickOnElementUsingJavascript(menuItem);
    }

    public void clickOnProfileMenuItem(String profileMenuItem) {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        waitForElementToAppear(profileDropDownCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        WebElement profileDropdown = getDriver().findElement(By.cssSelector(profileDropDownCssSelector));
        if (Boolean.parseBoolean(profileDropdown.getAttribute("aria-expanded")) == false) {
            clickOnElementUsingJavascript(profileDropdown);
        }
        WebElement actionItem = getElementFromList(profileDropDownMenuItemsListCssSelector, profileMenuItem);
        waitABit(1000);
        actionItem.click();
    }

    public void confirmSignout() {
        element(confirmLogoutButton).waitUntilClickable();
        confirmLogoutButton.click();
    }
}