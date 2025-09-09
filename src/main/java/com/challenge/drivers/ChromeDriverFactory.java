package com.challenge.drivers;

import com.challenge.datareader.PropertyReader;
import com.challenge.utils.logs.LogsManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ChromeDriverFactory extends AbstractDriver {
    private ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setAcceptInsecureCerts(true);
        switch (PropertyReader.getProperty("executionType"))
        {
            case "LocalHeadless" -> chromeOptions.addArguments("--headless=new");
            case  "Remote" ->
            {
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--headless=new");
            }
        }
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        return chromeOptions;
    }

    @Override
    public WebDriver create() {
        if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Local") ||
                PropertyReader.getProperty("executionType").equalsIgnoreCase("LocalHeadless") )
        {
            return new ChromeDriver(getChromeOptions());
        }
        else if (PropertyReader.getProperty("executionType").equalsIgnoreCase("Remote")) {
            try {
                return new RemoteWebDriver(
                        new URI("http://"+ remoteHost+ ":" +remotePort + "/wd/hub").toURL(), getChromeOptions()
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
