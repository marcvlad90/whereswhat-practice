package com.tests.apiCalls;

import com.steps.api.ApiLoginSteps;
import com.tests.BaseTest;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Test001ApiLogin extends BaseTest {

    @Steps
    private ApiLoginSteps apiLoginSteps;

    @Test
    public void test001ApiLogin() {
        apiLoginSteps.loginAsAdmin();
        apiLoginSteps.checkThatYouAreLoggedIn();
        apiLoginSteps.loginAsRegularUser();
        apiLoginSteps.checkThatYouAreLoggedIn();

    }
}
