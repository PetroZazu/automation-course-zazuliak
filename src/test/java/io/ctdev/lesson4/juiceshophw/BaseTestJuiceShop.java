package io.ctdev.lesson4.juiceshophw;

import io.ctdev.framework.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class BaseTestJuiceShop {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public List<String> itemsDescriptionPage1 = new ArrayList<>();

    @BeforeClass
    public void beforeClass() {
        System.out.println("before class1");
        driver = getDriver();
        wait = new WebDriverWait(driver, 7);
        //Open Web Page
        driver.get(TestConfig.cfg.juiceShopProd());
        //Useing cookies adjustment, banner and cookies consent pop-ups will be closed
        driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
        driver.manage().addCookie(new Cookie("welcomebanner_status", "dismiss"));
        driver.navigate().refresh();

    }

    public static WebElement waitUntilDisplayed(By locator, int howLongToWaitSeconds) {
       try {
           wait = new WebDriverWait(driver, howLongToWaitSeconds);
           return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
       } finally {
           wait = new WebDriverWait(driver, 7);
       }

    }


    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
