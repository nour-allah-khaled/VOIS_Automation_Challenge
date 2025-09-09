package com.challenge.pages;

import com.challenge.drivers.GUIFactory;
import com.challenge.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class Page_02 {
    private final GUIFactory driver;
    private final By page_3btn = By.xpath("//a[@aria-label='Page 3']");
    private final By page2_total_searchResultnum = By.xpath("//ol[@id='b_results'] /li[@class='b_algo']");

    public Page_02(GUIFactory driver) {
        this.driver = driver;
    }
    @Step("Get total search results on Page 2")
    public int getTotalResults(){
        LogsManager.info("Getting total search results on Page 2");
        return driver.getElementAction().findElements(page2_total_searchResultnum).size();
    }
    @Step("Scroll down and click on Page 3 button")
    public Page_03 navigateToPage3() {
        driver.getElementAction().scrollToElement(page_3btn);
        driver.getElementAction().clicking(page_3btn);
        LogsManager.info("Navigated to Page 3");
        return new Page_03(driver);
    }
    //Assertions
    public Page_02 verifyCurrentUrl(String expectedUrl) {
        String actualUrl = driver.getBrowserAction().getCurrentUrl();
        driver.getHardAssertion().assertEquals(actualUrl, expectedUrl, "URL Mismatch on Page 2");
        return this;
    }

}
