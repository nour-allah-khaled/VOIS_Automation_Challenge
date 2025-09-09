package com.challenge.listeners;

import com.challenge.assertions.SoftAssertion;
import com.challenge.datareader.PropertyReader;
import com.challenge.drivers.WebDriverProvider;
import com.challenge.media.ScreenShotMedia;
import com.challenge.utils.AllureUtil;
import com.challenge.utils.logs.LogsManager;

import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestNGListeners implements IExecutionListener, IInvokedMethodListener, ITestListener {
    public void onExecutionStart() {
        AllureUtil.cleanAllureResults();
        LogsManager.info("Cleaned Allure results");
        LogsManager.info("Starting execution");
        PropertyReader.loadProperties();
        LogsManager.info("Loaded properties");
    }
    public void onExecutionFinish() {
        LogsManager.info("Test Execution Finished");
        try {
            Runtime.getRuntime().exec("cmd.exe /c start generate-allureReport.bat");
        }
        catch (Exception e) {
            LogsManager.error("Error while generating Allure report: " + e.getMessage());
        }
    }
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        WebDriver driver = null;
        if (method.isTestMethod()) {
            if (testResult.getInstance() instanceof WebDriverProvider provider) {
                driver = provider.getWebDriver();
                ScreenShotMedia.Screen_shot(driver, "ScreenShot-" + testResult.getName());
                SoftAssertion.assertAll();
                if (testResult.getStatus() == ITestResult.FAILURE) {
                    LogsManager.info("Test Execution Failed");
                    ScreenShotMedia.Screen_shot(driver, "failed-" + testResult.getName());
                }
                switch (testResult.getStatus()) {
                    case ITestResult.SUCCESS -> {
                        LogsManager.info("Test " + testResult.getName() + " passed.");
                    }
                    case ITestResult.FAILURE -> {
                        LogsManager.error("Test " + testResult.getName() + " failed.");
                    }
                    case ITestResult.SKIP -> {
                        LogsManager.warn("Test " + testResult.getName() + " skipped.");
                    }
                }
                AllureUtil.attachLogs();
            }
        }
    }
    public void onTestStart(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " started");
    }
    public void onTestSuccess(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " passed");
    }
    public void onTestSkipped(ITestResult result) {
        LogsManager.info("Test Case " + result.getName() + " skipped");

    }
}