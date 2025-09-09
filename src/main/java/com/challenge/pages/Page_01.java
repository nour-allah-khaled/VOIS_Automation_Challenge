package com.challenge.pages;

import com.challenge.drivers.GUIFactory;
import com.challenge.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class Page_01 {
    private final GUIFactory driver;
    private final By search_field = By.xpath("//textarea[@id='sb_form_q']");
    private final By search_iconbtn = By.cssSelector("#search_icon");
    private final By Edge_related_searchPage1 = By.xpath("//ul[@class='b_vList b_divsec']");
    private final By Chrome_related_searchPage1 = By.xpath("//div[@id='brsv3']//a[@target='_blank']");
    private final By Firefox_related_searchPage1 = By.xpath("//div[@id='brsv3']//a[@target='_blank']");
    private final By page_2btn = By.xpath("//a[@aria-label='Page 2']");
    public Page_01(GUIFactory driver) {
        this.driver = driver;
    }
    @Step("Enter search data: {searchText}")
    public Page_01 EnterSearchData(String searchText) {
        driver.getElementAction().sendkeys(search_field, searchText);
        return this;
    }
    @Step("Click search icon button")
    public Page_01 ClickSearchIcon() {
        driver.getElementAction().clicking(search_iconbtn);
        return this;
    }
    public By getRelatedSearchLocator() {
        String browserName = ((org.openqa.selenium.HasCapabilities) driver.getDriver()).getCapabilities().getBrowserName().toLowerCase();
        if (browserName.contains("edge")) {
            return Edge_related_searchPage1;
        } else if (browserName.contains("chrome")) {
            return Chrome_related_searchPage1;
        } else if (browserName.contains("firefox")) {
            return Firefox_related_searchPage1;
        } else {
            throw new UnsupportedOperationException("Unsupported browser: " + browserName);
        }
    }
    @Step("Scroll down to Related searches for Vodafone and check for 'Vodafone' text")
    public boolean ScrollToRelatedSearch() {
        driver.getElementAction().scrollToElement(getRelatedSearchLocator());
        List<WebElement> relatedsearchElm = driver.getElementAction().findElements(getRelatedSearchLocator());
        for (WebElement element : relatedsearchElm) {
            String TXT = element.getText();
            if (TXT.contains("Vodafone")) {
                LogsManager.info("Related search contains 'Vodafone'");
                return true;
            }
        }
        LogsManager.info("Related search does not contain 'Vodafone'");
        return false;
    }
    @Step("Click on Page 2 button")
    public Page_02 ClickPage2btn() {
        driver.getElementAction().scrollToElement(page_2btn);
        driver.getElementAction().clicking(page_2btn);
        return new Page_02(driver);
    }
    // Assertions
    public Page_01 verifyCurrentUrl(String expectedUrl) {
        String actualUrl = driver.getBrowserAction().getCurrentUrl();
        driver.getSoftAssertion().assertEquals(actualUrl, expectedUrl, "URL does not match the expected value.");
        return this;
    }
    public Page_01 RelatedSearchContainsText() {
        boolean containsVodafone = ScrollToRelatedSearch();
        driver.getSoftAssertion().assertTrue(containsVodafone, "Related search does not contain 'Vodafone'.");
        return this;
    }
}
