package com.pages;

import org.junit.Assert;

public class SettingsPage extends AbstractPage {
    private static final String locationsListCssSelector = "#profile-Locations>div>div.ellipsis";

    public void checkIfLocationOrItemIsPresentOrNot(boolean shouldBePresent, String name) {
        if (shouldBePresent) {
            Assert.assertTrue(String.format("%s location was not found!", name),
                    checkIfElementExists(getElementFromList(locationsListCssSelector,
                            name)));
        } else {
            Assert.assertFalse(String.format("%s location was found and it should not!", name),
                    checkIfElementExists(getElementFromList(locationsListCssSelector,
                            name)));
        }
    }
}
