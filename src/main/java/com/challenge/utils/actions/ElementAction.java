package com.challenge.utils.actions;

import com.challenge.utils.WaitManager;
import com.challenge.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementAction {
    private final WebDriver driver;
    private WaitManager wait;

    public ElementAction(WebDriver driver) {
        this.driver = driver;
        wait = new WaitManager(driver);
    }
    //clicking
    public void clicking(By locator) {
        wait.fluentWait().until(
                d-> {
                    try {
                        WebElement element = driver.findElement(locator);
                        scrollToElement(locator);
                        element.click();
                        LogsManager.info("Clicked on element: " + locator.toString());
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }
    //typing
    public void sendkeys(By locator, String text) {
        wait.fluentWait().until(
                d-> {
                    try {
                        WebElement element = driver.findElement(locator);
                        scrollToElement(locator);
                        element.clear();
                        element.sendKeys(text);
                        LogsManager.info("Typed text '" + text + "' into element: " + locator);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }
    //getting text
    public String getText(By locator) {
        return wait.fluentWait().until(
                d-> {
                    try {
                        WebElement element = driver.findElement(locator);
                        scrollToElement(locator);
                        String text = element.getText();
                        LogsManager.info("Got text '" + text + "' from element: " + locator);
                        return !text.isEmpty() ? text : null;
                    } catch (Exception e) {
                        return null;
                    }
                }
        );
    }
    //find element
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }
    //find elements
    public List<WebElement> findElements(By locator) {
        return  driver.findElements(locator);
    }
    //scroll to element using js
    public void scrollToElement(By locator){
        ((org.openqa.selenium.JavascriptExecutor) driver).
                executeScript("""
                  arguments[0].scrollIntoView({behavior: "auto", block: "center", inline: "center" });""",findElement(locator));
    }
}
