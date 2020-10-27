package io.ctdev.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {
    protected WebDriver driver;
    protected final int TIME_OUT = 10;
    private WebDriverWait wait;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIME_OUT);
    }


    protected abstract void openPage();

    public void closeWelcomeAndCookiesPopUps() {
        System.out.println("Using cookies adjustment, banner and cookies consent pop-ups will be closed");
        driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
        driver.manage().addCookie(new Cookie("welcomebanner_status", "dismiss"));
        driver.navigate().refresh();
    }

    public WebElement waitUntilDisplayed(By locator, int howLongToWaitSeconds) {
        try {
            wait = new WebDriverWait(driver, howLongToWaitSeconds);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } finally {
            wait = new WebDriverWait(driver, TIME_OUT);
        }

    }

    public WebElement waitForElementToBeClickable(By locator, int howLongToWaitSeconds) {
        try {
            wait = new WebDriverWait(driver, howLongToWaitSeconds);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } finally {
            wait = new WebDriverWait(driver, TIME_OUT);
        }
    }

    public static void waitForPageToLoad(WebDriver driver) {
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

    public void scrollToTheElement(By locator) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        System.out.println("Performing scroll to the element: " + locator);
        jse.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(locator));

    }


}




