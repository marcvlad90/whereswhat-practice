package com.tests.UI;

import com.steps.frontend.HeaderSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.flowsteps.LoginFlowSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test001UiLogin extends BaseTest {
    @Steps
    private LoginFlowSteps loginFlowSteps;
    @Steps
    private HomeSteps homeSteps;
    @Steps
    private HeaderSteps headerSteps;

    @Test
    public void test001UiLogin() {
        loginFlowSteps.loginAsAdmin();
        homeSteps.checkThatYouAreLoggedIn();
        headerSteps.logout();
        loginFlowSteps.loginAsRegularUser();
        homeSteps.checkThatYouAreLoggedIn();
    }
}
