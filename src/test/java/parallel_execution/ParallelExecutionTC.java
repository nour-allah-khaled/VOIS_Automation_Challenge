package parallel_execution;

import com.challenge.drivers.GUIFactory;
import com.challenge.pages.Page_01;
import com.challenge.pages.Page_02;
import com.challenge.utils.logs.LogsManager;
import io.qameta.allure.*;
import org.testng.annotations.*;

@Epic("Parallel Execution")
@Feature("Search Functionality and Pagination on Different Browsers")
@Story("As a user i want to search for a specific text and pagination functionality on different browsers")
@Severity(SeverityLevel.CRITICAL)
@Owner("Nour Allah Khaled")
public class ParallelExecutionTC extends BaseClassTC {

    @Parameters(value = "browserType")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("Chrome") String browser) {
        driver = new GUIFactory(browser);
        LogsManager.info("Driver Initialized for " + browser + " browser");
        driver.getBrowserAction().maximize();
        LogsManager.info("Navigating to URL: " + URL);
        driver.getBrowserAction().navigateTo(URL);
    }

    @Description("verify that the related searches contain the search text")
    @Test()
    public void verifyThatRelatedSearchesContainsVodafone() {
        LogsManager.info("This test is running on Edge browser");
        new Page_01(driver).EnterSearchData(testData.getJsonData("Text")).
                ClickSearchIcon().verifyCurrentUrl(Page1_ExpectedURL).RelatedSearchContainsText();
        LogsManager.info("Test completed successfully on Edge browser and navigated to the expected URL which is: " + Page1_ExpectedURL +
                " and the related searches contain the search text which is: " + testData.getJsonData("Text"));
    }

    @Description("Verify that  the total results on page 2 and page 3 are equal or not then quit the browser")
    @Test()
    public void verifyThatTwoPagesHaveTheSameResults() {
        LogsManager.info("This test is running on Chrome browser");
        int resultsForPage2 = new Page_01(driver).EnterSearchData(testData.getJsonData("Text")).
                ClickSearchIcon()
                .verifyCurrentUrl(Page1_ExpectedURL)
                .RelatedSearchContainsText()
                .ClickPage2btn()
                .verifyCurrentUrl(Page2_ExpectedURL)
                .getTotalResults();

        new Page_02(driver)
                .navigateToPage3()
                .VerifyCurrentURLPage3(Page3_ExpectedURL).getTotalResultsPage3(Integer.parseInt(testData.getJsonData("Expected_Count")))
                .EqualResult(resultsForPage2);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quitDriver();
        LogsManager.info("Driver Closed");
    }
}
