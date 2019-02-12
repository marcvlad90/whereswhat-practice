package com.pages;

import com.tools.constants.Constants;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HeaderPage extends AbstractPage {
    @FindBy(css = ".userImage")
    private WebElement profileImageElement;
    @FindBy(id = "log-out")
    private WebElement confirmLogoutButton;
    private String menuItemsCssSelector = "#menu a[href='#textToReplace()']";
    private final String profileDropDownMenuItemsListCssSelector = ".dropdown.navbar-right.open .dropdown-menu>li";
    private final String spinnerElementCssSelector = ".spinner";

    public void navigateToMenu(String menu) {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        waitForTextToAppear(menu, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        WebElement menuItem = getDriver().findElement(By.cssSelector(menuItemsCssSelector.replace("textToReplace()", menu.toLowerCase())));
        waitForElementToBeClickable(menuItem, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        menuItem.click();
    }

    public void clickOnProfileMenuItem(String profileMenuItem) {
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        profileImageElement.click();
        WebElement actionItem = getElementFromList(profileDropDownMenuItemsListCssSelector, profileMenuItem);
        actionItem.click();
    }

    public void confirmSignout() {
        element(confirmLogoutButton).click();
    }
}
