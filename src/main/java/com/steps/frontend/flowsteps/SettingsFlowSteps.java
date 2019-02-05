package com.steps.frontend.flowsteps;

import com.steps.frontend.AbstractSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.SettingsSteps;
import com.tools.constants.Constants;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class SettingsFlowSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    @Steps
    private HeaderSteps headerSteps;
    @Steps
    private SettingsSteps settingsSteps;

    @StepGroup
    public void checkIfLocationOrItemIsPresentOrNot(boolean shouldBePresent) {
        headerSteps.clickOnProfileMenuItem(Constants.PROFILE_MENU_ITEM_SETTINGS);
        settingsSteps.checkIfLocationOrItemIsPresentOrNot(shouldBePresent);
    }
}
