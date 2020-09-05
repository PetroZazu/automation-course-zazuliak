package io.ctdev.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class JuiceShopGeneralTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
        driver.get("http://18.217.145.6");
        driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
        driver.manage().addCookie(new Cookie("welcomebanner_status", "dismiss"));
        driver.navigate().refresh();
    }


    @Test
    public void clickTest() throws InterruptedException {
        WebElement milkPocket = driver.findElement(By.cssSelector("span[class*='mat-button-wrapper'] img[class='logo']"));
        milkPocket.click();
        Thread.sleep(3000);
        milkPocket.click();
        Thread.sleep(3000);
    }

    @Test
    public void getAttributeTest () {
        WebElement sideMunuButton = driver.findElement(By.xpath("//span[contains(@class,'mat-button-wrapper')]/mat-icon[contains(text(), 'menu')]"));
        System.out.println("side menu button 'Class' attribute content is: " + sideMunuButton.getAttribute("class"));
    }

    @Test
    public void visibilityTest () throws InterruptedException {
        WebElement itemsPerPageCounter = driver.findElement(By.xpath("//div[contains(@class, 'mat-paginator-outer-container')]//div[contains(text(), 'Items per page')]"));
        Thread.sleep(3000);
        itemsPerPageCounter.isDisplayed(); 
    }


    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
