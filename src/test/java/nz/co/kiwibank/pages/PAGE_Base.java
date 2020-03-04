package nz.co.kiwibank.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * Base page class
 * for common elements and common methods
 *
 * @author Lin
 * @since 26/02/2020
 */
public class PAGE_Base {
    protected final static long timeout = 30;
    protected static final Logger LOGGER = Logger.getLogger(PAGE_Base.class.getName());

    WebDriver driver = null;

    public PAGE_Base(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        waitElement(element).click();
    }

    protected void sendKeys(WebElement element, String inputTerm) {
        element.sendKeys(inputTerm);
    }

    protected void clearBeforeSendKeys(WebElement element, String inputTerm) {
        waitElement(element);
        element.clear();
        sendKeys(element, inputTerm);
    }

    protected void selectByVisibleText(WebElement element, String text) {
        waitElement(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected  WebElement waitElement(WebElement element){
        waitForPageLoad(driver, timeout);
        waitForJQueryProcessing(driver, timeout);
        return waitAndScrollToElement(element);
    }

    protected WebElement waitAndScrollToElement(WebElement element) {
        int y = element.getLocation().getY();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + y + ");");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100);");

        return element;
    }

    protected void waitForPageLoad(WebDriver driver, long timeOutInSeconds) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(pageLoadCondition);
    }

    protected boolean waitForJQueryProcessing(WebDriver driver, long timeOutInSeconds) {
        boolean jQcondition = false;
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)) {
            }.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return jQuery.active == 0");
                }
            });

            jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return window.jQuery != undefined && jQuery.active === 0");
            return jQcondition;
        } catch (Exception e) {
            LOGGER.warning("jQuery either not present or not fully loaded");
        }
        return jQcondition;
    }
}
