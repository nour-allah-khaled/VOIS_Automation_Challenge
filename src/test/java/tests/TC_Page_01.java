package tests;

import com.challenge.drivers.GUIFactory;
import com.challenge.listeners.TestNGListeners;
import com.challenge.pages.Page_01;
import com.challenge.pages.Page_02;
import com.challenge.utils.logs.LogsManager;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parallel_execution.BaseClassTC;

@Epic("Parallel Execution")
@Feature("Search Functionality and Pagination")
@Story("As a user i want to search for a specific text and pagination functionality")
@Severity(SeverityLevel.CRITICAL)
@Owner("Nour Allah Khaled")
public class TC_Page_01 extends BaseClassTC {
    @BeforeMethod
  public void setup(){
     driver = new GUIFactory();
     driver.getBrowserAction().maximize();
     driver.getBrowserAction().navigateTo(URL);
  }
  @Description("Verify that  the total results on page 2 and page 3 are equal or not then quit the browser")
  @Test
  public void test() {
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
  @AfterMethod
    public void quit(){
        driver.quitDriver();
        LogsManager.info("Browser is closed");
    }
}
