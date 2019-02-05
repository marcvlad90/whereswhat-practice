package com.pages;

import com.tools.constants.Constants;
import com.tools.constants.EnvironmentConstants;

import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {

    @FindBy(css = "input#email")
    private WebElement email;
    @FindBy(css = "input#pass")
    private WebElement password;
    @FindBy(css = ".userImage")
    private WebElement profileImageElement;
    @FindBy(css = "input[class*='signin']")
    private WebElement signIn;

    public void enterEmail(String emailValue) {
        this.email.sendKeys(emailValue);
    }

    public void enterPassword(String passwordValue) {
        this.password.sendKeys(passwordValue);
    }

    public void submit() {
        signIn.click();
        waitForElementToAppear(profileImageElement, Constants.EXPLICIT_WAIT_TIME_IN_SECONDS);
    }

    public void login() {
        enterEmail(EnvironmentConstants.ADMIN_USER);
        enterPassword(EnvironmentConstants.ADMIN_USER_PASS);
        submit();
    }
}
