package com.steps.frontend;

import com.pages.LoginPage;
import com.tools.constants.EnvironmentConstants;

import net.thucydides.core.annotations.Step;

public class LoginSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private LoginPage loginPage;

    @Step
    public void loginAsAdmin() {
        loginPage.enterEmail(EnvironmentConstants.ADMIN_USER);
        loginPage.enterPassword(EnvironmentConstants.ADMIN_USER_PASS);
        loginPage.submit();
    }

    @Step
    public void loginAsRegularUser() {
        loginPage.enterEmail(EnvironmentConstants.REGULAR_USER);
        loginPage.enterPassword(EnvironmentConstants.REGULAR_USER_PASS);
        loginPage.submit();
    }
}
