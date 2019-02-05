package com.steps.frontend;

import com.pages.SettingsPage;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Location;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

public class SettingsSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;
    private SettingsPage settingsPage;

    @Step
    public void checkIfLocationOrItemIsPresentOrNot(boolean shouldBePresent) {
        Location location = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LOCATION);
        settingsPage.checkIfLocationOrItemIsPresentOrNot(shouldBePresent, location.getName());
    }
}
