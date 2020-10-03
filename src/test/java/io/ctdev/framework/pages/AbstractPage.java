package io.ctdev.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {
    private WebDriver driver;
    protected final int TIME_OUT = 10;
    private WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        //wait = new WebDriverWait(driver, 5);
    }


    protected abstract void openPage();

    public WebElement waitUntilDisplayed(By locator, int howLongToWaitSeconds) {
        try {
            wait = new WebDriverWait(driver, howLongToWaitSeconds);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } finally {
            wait = new WebDriverWait(driver, TIME_OUT);
        }

    }

    public void waitForPageToLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoad = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };

        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        try {
            wait.until(pageLoad);
        } catch (Throwable pageLoadWaitError) {
            System.out.println("Page load time was 30 seconds");
        }
    }


}




