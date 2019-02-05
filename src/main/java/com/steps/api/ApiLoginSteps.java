package com.steps.api;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.HeaderConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Login;
import com.tools.entities.User;
import com.tools.factories.LoginFactory;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

public class ApiLoginSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void loginAsAdmin() {
        Login loginRequest = LoginFactory.getAdminLoginInstance();
        User userResponse = createResource(ApiUrlConstants.LOGIN, loginRequest, User.class);
        loginRequest.setUser((User)InstanceUtils.mergeObjects(loginRequest.getUser(), userResponse));
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.USER, loginRequest.getUser());
        AbstractApiSteps.extraHeaders.put(HeaderConstants.API_HEADER_AUTHORIZATION, "Basic " + userResponse.getAuthenticationToken());
    }

    @Step
    public void loginAsRegularUser() {
        Login loginRequest = LoginFactory.getRegularUserLoginInstance();
        User userResponse = createResource(ApiUrlConstants.LOGIN, loginRequest, User.class);
        loginRequest.setUser((User)InstanceUtils.mergeObjects(loginRequest.getUser(), userResponse));
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.USER, loginRequest.getUser());
        AbstractApiSteps.extraHeaders.put(HeaderConstants.API_HEADER_AUTHORIZATION, "Basic " + userResponse.getAuthenticationToken());
    }

    @Step
    public void checkThatYouAreLoggedIn() {
        User user = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
        Assert.assertTrue("You are not logged in!", !user.getAuthenticationToken().isEmpty());
    }
}
