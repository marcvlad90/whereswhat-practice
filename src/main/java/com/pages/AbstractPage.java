package com.pages;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tools.constants.Constants;

public class AbstractPage extends PageObject {

    public void deleteFileIfExists(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    public void waitForElementToDisappear(final String cssSelector, final int noOfSeconds) {
        @SuppressWarnings("deprecation")
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(Constants.WAIT_TIME_MAXIMUM_IN_SECONDS, TimeUnit.SECONDS)
                .pollingEvery(Constants.WAIT_TIME_FLUENT_WAIT_POLLING_IN_MILISECONDS, TimeUnit.MILLISECONDS)
                .ignoring(ElementNotVisibleException.class).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(org.openqa.selenium.By
                .cssSelector(cssSelector)));
    }

    public void waitForElementToBeClickable(WebElement element, final int noOfSeconds) {
        @SuppressWarnings("deprecation")
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
        .withTimeout(Constants.WAIT_TIME_MAXIMUM_IN_SECONDS, TimeUnit.SECONDS)
        .pollingEvery(Constants.WAIT_TIME_FLUENT_WAIT_POLLING_IN_MILISECONDS, TimeUnit.MILLISECONDS)
        .ignoring(ElementNotVisibleException.class).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
        .ignoring(ElementNotInteractableException.class);
        wait = new WebDriverWait(getDriver(), noOfSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickOnElementUsingJavascript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    public void waitForListToLoad(final List<WebElement> list, final int timeoutSeconds, final boolean assertListNotEmpty) {
        int counter = 0;
        while ((list.size() == 0) && (counter < (timeoutSeconds * 10))) {
            waitABit(100);
            counter++;
        }
        if (assertListNotEmpty) {
            Assert.assertTrue("List is empty", list.size() > 0);
        }
    }

    public void waitForListToLoad(final String listIdentifierCssSelector, final int timeoutSeconds, final boolean assertListNotEmpty) {
        int counter = 0;
        List<WebElement> listItems = getDriver().findElements(By.cssSelector(listIdentifierCssSelector));
        while ((listItems.size() == 0) && (counter < (timeoutSeconds * 10))) {
            waitABit(100);
            counter++;
        }
        if (assertListNotEmpty) {
            Assert.assertTrue("List is empty", listItems.size() > 0);
        }
    }

    public void waitForElementToAppear(final WebElement element, final int noOfSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), noOfSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToAppear(final String cssSelector, final int noOfSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), noOfSeconds);
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(cssSelector))));
    }

    public void scrollToPageBottom() {
        evaluateJavascript("window.scrollTo(0,document.body.scrollHeight);");
    }

    public int getElementsListSize(String elementsListCssSelector) {
        waitForListToLoad(elementsListCssSelector, Constants.WAIT_TIME_MAXIMUM_IN_SECONDS, false);
        List<WebElement> elements = getDriver().findElements(org.openqa.selenium.By.cssSelector(elementsListCssSelector));
        return elements.size();
    }

    public void clickOnElementFromList(String elementsListCssSelector, String elementIdentifierText) {
        getElementFromList(elementsListCssSelector, elementIdentifierText).click();
    }

    public WebElement getElementFromList(String elementsListCssSelector, String elementIdentifierText) {
        if (!getDriver().findElements(org.openqa.selenium.By.cssSelector(elementsListCssSelector)).isEmpty()) {
            List<WebElement> elements = getDriver().findElements(org.openqa.selenium.By.cssSelector(elementsListCssSelector));
            for (WebElement element : elements) {
                if (element.getText().contains(elementIdentifierText)) {
                    return element;
                }
            }
        }
        return null;

    }

    public WebElement getElementFromList(List<WebElement> elements, String elementIdentifierText) {
        for (WebElement element : elements) {
            if (element.getText().contains(elementIdentifierText)) {
                return element;
            }
        }
        return null;
    }

    public void waitForElementsByCssLocator(String cssLocator) {
        (new WebDriverWait(getDriver(), 20))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(org.openqa.selenium.By.cssSelector(cssLocator)));
    }

    public <T> void verifyListOfObjects(List<T> list1, List<T> list2, String matchElement)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        List<T> localList2 = new ArrayList<T>(list2);

        Assert.assertTrue("The lists do not have the same size", list1.size() == list2.size());

        for (T item : list1) {
            T itemInList2 = getObjectByMatchingElement(localList2, matchElement,
                    PropertyUtils.getProperty(item, matchElement).toString());
            for (Field field : item.getClass().getDeclaredFields()) {

                field.setAccessible(true);

                if (field.get(item) == null) {
                    Assert.assertNull(field.get(itemInList2));

                } else {
                    Assert.assertTrue(
                            "<< " + field.getName() + " >> doesn't match !! Expected : " + field.get(item) + " Actual "
                                    + field.get(itemInList2),
                            (String.valueOf(field.get(itemInList2)).contains(String.valueOf(field.get(item)))));
                }
            }
            localList2.remove(itemInList2);
        }
    }

    public <T> T getObjectByMatchingElement(List<T> list, String matchElement, String matchValue)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        for (T obj : list) {
            Object value = PropertyUtils.getProperty(obj, matchElement);
            if (((String)value).contains(matchValue)) {
                return obj;
            }
        }
        Assert.assertTrue("The object with type " + matchElement + " wasn't found", false);
        return null;
    }

    public <T> void verifyObjects(T obj1, T obj2)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        for (Field field : obj1.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (field.get(obj1) == null) {
                Assert.assertNull(field.get(obj2));

            } else {
                Assert.assertTrue(
                        "<< " + field.getName() + " >> doesn't match !! Expected : " + field.get(obj1) + " Actual "
                                + field.get(obj2),
                        ((String)(field.get(obj2))).contentEquals((String)field.get(obj1)));
            }
        }
    }

    public <T> void verifyObjectsIgnoreNull(T obj1, T obj2)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        for (Field field : obj1.getClass().getDeclaredFields()) {

            field.setAccessible(true);

            if (!((field.get(obj1) == null) && (field.get(obj2) == null))) {
                Assert.assertTrue("<< " + field.getName() + " >> doesn't match !! Expected : " + field.get(obj1)
                        + " Actual " + field.get(obj2),
                        ((String)(field.get(obj2))).contains((String)field.get(obj1)));
            }
        }
    }

    public void scrollToElementByName(WebElementFacade e) {
        ((JavascriptExecutor)getDriver())
                .executeScript("" + e + ".scrollTo(0, " + e + ".scrollHeight)");
    }

    public boolean checkIfElementExists(final WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (NullPointerException e1) {
            return false;
        }
    }

    public boolean checkIfElementExists(String elementCssSelector) {
        if (getDriver().findElements(By.cssSelector(elementCssSelector)).isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkIfChildElementExists(final WebElement parentElement, String elementCssSeletor) {
        if (parentElement.findElements(org.openqa.selenium.By.cssSelector(elementCssSeletor)).isEmpty()) {
            return false;
        }
        return true;
    }
}