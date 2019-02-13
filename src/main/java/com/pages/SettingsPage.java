package com.pages;

import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;

public class SettingsPage extends AbstractPage {
    private static final String locationsListCssSelector = "#profile-Locations>div>div.ellipsis";

    public void checkIfLocationOrItemIsPresentOrNot(boolean shouldBePresent, String name) {
        boolean staleElementException = false;
        do {
            try {
                if (shouldBePresent) {
                    waitForTextToAppear(name);
                    Assert.assertTrue(String.format("%s location was not found!", name),
                            checkIfElementExists(getElementFromList(locationsListCssSelector,
                                    name)));
                } else {
                    Assert.assertFalse(String.format("%s location was found and it should not!", name),
                            checkIfElementExists(getElementFromList(locationsListCssSelector,
                                    name)));
                }
            } catch (StaleElementReferenceException e) {
                staleElementException = true;
            }
        } while (staleElementException);
    }
}
