package com.pages;

import com.tools.constants.Constants;
import com.tools.constants.EnvironmentConstants;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class HeaderPage extends AbstractPage {
    @FindBy(id = "log-out")
    private WebElement confirmLogoutButton;
    private String menuItemsCssSelector = "#menu a[href='#textToReplace()']";
    private final String profileDropDownMenuItemsListCssSelector = ".dropdown.navbar-right.open .dropdown-menu>li";
    private final String spinnerElementCssSelector = ".spinner";
    private final String profileDropDownCssSelector = ".dropdown-toggle";

    public void navigateToMenu(String menu) {
        waitForTextToAppear(menu, Constants.WAIT_TIME_MAXIMUM_IN_MILISECONDS);
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
        actionItem.click();
    }

    public void logout() {
        getDriver().manage().deleteAllCookies();
        int numberOfAttempts = 0;
        do {
            try {
                getDriver().navigate().to(EnvironmentConstants.BASE_URL);
                clickOnProfileMenuItem(Constants.PROFILE_MENU_ITEM_SIGNOUT);
                element(confirmLogoutButton).waitUntilClickable();
                confirmLogoutButton.click();
            } catch (WebDriverException e) {
                numberOfAttempts++;
                e.getMessage();
            }
        } while (!containsAllText("LOGIN", "FREE SIGN UP") && (numberOfAttempts < 10));
    }
}