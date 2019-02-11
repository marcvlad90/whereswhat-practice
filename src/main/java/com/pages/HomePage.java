package com.pages;

import com.tools.constants.Constants;
import com.tools.constants.EnvironmentConstants;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HomePage extends AbstractPage {
    @FindBy(css = ".sign-in-a")
    private WebElement loginButton;
    private final String spinnerElementCssSelector = ".spinner";

    public void clickLogin() {
        waitForElementToAppear(loginButton, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);
        loginButton.click();
        waitForElementToDisappear(spinnerElementCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS);

    }

    public void navigateToHomePage() {
        getDriver().get(EnvironmentConstants.BASE_URL);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Constants.WAIT_TIME_MAXIMUM_IN_SECONDS, TimeUnit.SECONDS);
    }
}
