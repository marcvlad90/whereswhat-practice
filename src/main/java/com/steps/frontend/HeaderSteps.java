package com.steps.frontend;

import com.pages.HeaderPage;
import com.tools.constants.Constants;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.Step;

public class HeaderSteps extends AbstractSteps {

    private static final long serialVersionUID = 1L;
    private HeaderPage headerPage;
    @FindBy(css = ".userImage")
    private final String spinnerElementCssSelector = ".spinner";

    @Step
    public void navigateToMenu(String menu) {
        headerPage.navigateToMenu(menu);
    }

    @Step
    public void clickOnProfileMenuItem(String profileMenuItem) {
        headerPage.clickOnProfileMenuItem(profileMenuItem);
    }

    @Step
    public void logout() {
        headerPage.clickOnProfileMenuItem(Constants.PROFILE_MENU_ITEM_SIGNOUT);
        headerPage.confirmSignout();
    }
}
