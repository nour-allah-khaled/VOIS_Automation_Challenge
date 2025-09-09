package com.challenge.drivers;

import com.challenge.datareader.PropertyReader;
import com.challenge.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class FirefoxDriverFactory extends AbstractDriver {
    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // Add Firefox-specific options here
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-popup-blocking");
        firefoxOptions.addArguments("--disable-gpu");
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--disable-dev-shm-usage");
        switch (PropertyReader.getProperty("executionType"))
        {
            case "LocalHeadless" -> firefoxOptions.addArguments("--headless");
            case  "Remote" ->
            {
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--disable-extensions");
                firefoxOptions.addArguments("--headless=new");
            }
        }
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return firefoxOptions;
    }
    @Override
    public WebDriver create() {
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless") )
        {
            return new FirefoxDriver(getFirefoxOptions());
        }
        else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI("http://"+ remoteHost+ ":" +remotePort + "/wd/hub").toURL(), getFirefoxOptions()
                );
            }
            catch (Exception e) {
                LogsManager.error("Error creating RemoteWebDriver: " + e.getMessage());
                throw new RuntimeException("Failed to create RemoteWebDriver", e);
            }

        }
        else {
            LogsManager.error("Invalid execution type: " + PropertyReader.getProperty("executionType"));
            throw new IllegalArgumentException("Invalid execution type: " + PropertyReader.getProperty("executionType"));
        }
    }
}
