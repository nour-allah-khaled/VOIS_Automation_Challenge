package com.challenge.assertions;

import com.challenge.utils.WaitManager;
import com.challenge.utils.actions.ElementAction;
import org.openqa.selenium.WebDriver;

public abstract class BaseAssertion {
    protected final WebDriver driver;
    protected final WaitManager wait;
    protected final ElementAction elemntAction;
    public BaseAssertion(WebDriver driver) {
        this.driver = driver;
        wait = new WaitManager(driver);
        elemntAction = new ElementAction(driver);
    }
    protected abstract void assertTrue(boolean condition, String message);
    protected abstract void assertFalse(boolean condition, String message);
    protected abstract void assertEquals(Object actual, Object expected, String message);
    protected abstract void assertNotEquals(Object actual, Object expected, String message);
}
