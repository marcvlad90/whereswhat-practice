package com.steps.api;

import com.tools.constants.ApiUrlConstants;
import com.tools.entities.Alert;
import com.tools.factories.AlertFactory;

import net.thucydides.core.annotations.Step;

public class ApiSettingSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void enableBookingRequestsNotifications() {
        Alert alertRequest = AlertFactory.getIndividualBookingRequestAlertInstance();
        updateResource(ApiUrlConstants.USER_SETTINGS, alertRequest);
    }
}
