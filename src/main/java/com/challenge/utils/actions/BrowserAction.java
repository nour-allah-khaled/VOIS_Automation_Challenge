package com.challenge.utils.actions;

import com.challenge.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class BrowserAction {
    private final WebDriver driver;

    public  BrowserAction(WebDriver driver) {
        this.driver = driver;
    }
    public void maximize() {
        driver.manage().window().maximize();
        LogsManager.info("Browser window maximized");
    }
    public String getCurrentUrl() {
        String getURL = driver.getCurrentUrl();
        LogsManager.info("Current URL: " + getURL);
        return getURL;
    }
    public void navigateTo(String url) {
        driver.get(url);
        LogsManager.info("Navigated to: " + url);
    }
    public void refresh() {
        driver.navigate().refresh();
    }
    public  void close() {
        driver.close();
    }
    public void openNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
    }
}
