package com.steps.frontend.flowsteps;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.api.ApiLoginSteps;
import com.steps.frontend.AbstractSteps;
import com.steps.frontend.HomeSteps;
import com.steps.frontend.LoginSteps;

public class LoginFlowSteps extends AbstractSteps {

    private static final long serialVersionUID = 1L;
    @Steps
    private HomeSteps homeSteps;
    @Steps
    private LoginSteps loginSteps;
    @Steps
    private ApiLoginSteps apiLoginSteps;

    @StepGroup
    public void loginAsAdmin() {
        apiLoginSteps.loginAsAdmin();
        homeSteps.navigateToHomePage();
        homeSteps.clickLogin();
        loginSteps.loginAsAdmin();
    }

    @StepGroup
    public void loginAsRegularUser() {
        apiLoginSteps.loginAsRegularUser();
        homeSteps.navigateToHomePage();
        homeSteps.clickLogin();
        loginSteps.loginAsRegularUser();
    }
}
