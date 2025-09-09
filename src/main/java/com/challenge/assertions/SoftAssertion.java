package com.challenge.assertions;

import com.challenge.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class SoftAssertion extends BaseAssertion {
    private static SoftAssert softAssert = new SoftAssert();
    private static boolean used = false;
    public SoftAssertion(WebDriver driver) {
        super(driver);
    }

    @Override
    public void assertTrue(boolean condition, String message) {
        used = true;
        softAssert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used = true;
        softAssert.assertFalse(condition, message);
    }

    @Override
    public void assertEquals(Object actual, Object expected, String message) {
        used = true;
        softAssert.assertEquals(actual, expected, message);
    }

    @Override
    protected void assertNotEquals(Object actual, Object expected, String message) {
        used = true;
        softAssert.assertNotEquals(actual, expected, message);
    }
    public static void assertAll() {
        if(!used) return;
        try {
            softAssert.assertAll();
        }
        catch (AssertionError e) {
            LogsManager.error("Assertion failed:", e.getMessage());
            throw e;
        }
        finally {
            softAssert = new SoftAssert(); // Reset for future use
        }
    }
}
