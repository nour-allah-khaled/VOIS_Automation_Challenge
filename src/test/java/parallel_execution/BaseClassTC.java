package parallel_execution;

import com.challenge.datareader.JsonReader;
import com.challenge.datareader.PropertyReader;
import com.challenge.drivers.GUIFactory;
import com.challenge.drivers.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseClassTC implements WebDriverProvider {
    protected GUIFactory driver;
    protected JsonReader testData;
    public  String URL;
    public String Page1_ExpectedURL;
    public String Page2_ExpectedURL;
    public String Page3_ExpectedURL;
    public BaseClassTC() {
    }

    @BeforeClass
    public void setupClass() {
        URL = PropertyReader.getProperty("Base_URL");
        Page1_ExpectedURL = PropertyReader.getProperty("Page_One_URL");
        Page2_ExpectedURL = PropertyReader.getProperty("Page_Two_URL");
        Page3_ExpectedURL = PropertyReader.getProperty("Page_Three_URL");
        testData = new JsonReader("Data");
    }

    @Override
    public WebDriver getWebDriver() {
        return driver.getDriver();
    }
}
