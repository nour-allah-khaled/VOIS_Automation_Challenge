package com.challenge.drivers;

import com.challenge.assertions.HardAssertion;
import com.challenge.assertions.SoftAssertion;
import com.challenge.datareader.PropertyReader;
import com.challenge.utils.actions.BrowserAction;
import com.challenge.utils.actions.ElementAction;
import com.challenge.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;

public class GUIFactory {
    private String browser ;
    private static ThreadLocal<WebDriver> ThreadLocalWebdriver = new ThreadLocal<>();
    public GUIFactory() {
        browser = PropertyReader.getProperty("browser");
        Browser browserEnum = Browser.valueOf(browser.toUpperCase());
        LogsManager.info("Starting driver for browser: " + browserEnum);
        AbstractDriver driverFactory = browserEnum.getDriverFactory();
        WebDriver driver = ThreadGuard.protect(driverFactory.create());
        ThreadLocalWebdriver.set(driver);
    }
    public GUIFactory(String browser) {
        Browser browserEnum = Browser.valueOf(browser.toUpperCase());
        LogsManager.info("Starting driver for browser: " + browserEnum);
        AbstractDriver driverFactory = browserEnum.getDriverFactory();
        WebDriver driver = ThreadGuard.protect(driverFactory.create());
        ThreadLocalWebdriver.set(driver);
    }
    public ElementAction getElementAction() {
        return new ElementAction(getDriver());
    }
    public BrowserAction getBrowserAction() {
        return new BrowserAction(getDriver());
    }
    public HardAssertion getHardAssertion() {
        return new HardAssertion(getDriver());
    }
    public SoftAssertion getSoftAssertion() {
        return new SoftAssertion(getDriver());
    }
    public static WebDriver getDriver() {
        return ThreadLocalWebdriver.get();
    }
    public void quitDriver() {
        ThreadLocalWebdriver.get().quit();
    }
}
