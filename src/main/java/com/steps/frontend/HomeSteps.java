package com.steps.frontend;

import com.pages.HomePage;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

public class HomeSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private HomePage homePage;

    @Step
    public void clickLogin() {
        homePage.clickLogin();
    }

    @Step
    public void navigateToHomePage() {
        homePage.navigateToHomePage();
    }

    @Step
    public void checkThatYouAreLoggedIn() {
        Assert.assertTrue("You are not logged in!", homePage.checkIfElementExists(".userImage"));
    }

}
