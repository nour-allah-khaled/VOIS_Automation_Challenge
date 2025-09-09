package com.challenge.pages;

import com.challenge.drivers.GUIFactory;
import com.challenge.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Page_03 {
    private final GUIFactory driver;
    private final By page3_total_searchResultnum = By.xpath("//ol[@id='b_results'] /li[@class='b_algo']");
    public Page_03(GUIFactory driver) {
        this.driver = driver;
    }
    @Step("Get total search results on Page 3")
    public int getTotalResultsPage3(){
        LogsManager.info("Getting total search results on Page 3");
        return driver.getElementAction().findElements(page3_total_searchResultnum).size();
    }
    //Assertion
    public Page_03 VerifyCurrentURLPage3(String expectedURL){
        String currentURL = driver.getBrowserAction().getCurrentUrl();
        driver.getHardAssertion().assertEquals(currentURL, expectedURL, "Current URL does not match the expected URL.");
        return this;
    }
    public Page_03 getTotalResultsPage3(int expectedResults){
        if (getTotalResultsPage3() == expectedResults) {
            LogsManager.info("Total results on page 2 match the expected value: " + expectedResults);
            driver.getHardAssertion().assertEquals(getTotalResultsPage3(), expectedResults, "Total results on page 2 match the expected value.");
        } else {
            LogsManager.error("Total results on page 2 do not match the expected value. Expected: " + expectedResults + ", Actual: " + getTotalResultsPage3());
        }
        return this;
    }
    public void EqualResult(int TotalPage2) {
        int TotalPage3 = getTotalResultsPage3();
            LogsManager.info("Total results on page 2 match the total results on page 3");
            driver.getHardAssertion().assertEquals(TotalPage3, TotalPage2, "Total results on page 2 match the total results on page 3");
        }

}
