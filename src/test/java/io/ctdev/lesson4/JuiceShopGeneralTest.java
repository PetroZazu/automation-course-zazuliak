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
    public void getAttributeTest() {
        WebElement sideMunuButton = driver.findElement(By.xpath("//span[contains(@class,'mat-button-wrapper')]/mat-icon[contains(text(), 'menu')]"));
        System.out.println("side menu button 'Class' attribute content is: " + sideMunuButton.getAttribute("class"));
    }

    @Test
    public void visibilityTest() throws InterruptedException {
        WebElement itemsPerPageCounter = driver.findElement(By.xpath("//div[contains(@class, 'mat-paginator-outer-container')]//div[contains(text(), 'Items per page')]"));
        Thread.sleep(3000);
        System.out.println("Is itemsPerPageCounter displayed on the page? " + itemsPerPageCounter.isDisplayed());
    }

    @Test
    public void getTextMethodTest() {
        WebElement mainSiteBunnerText = driver.findElement(By.xpath("//button[@aria-label='Back to homepage']/span/span"));
        System.out.println("Site Bunner Logo Text is: " + mainSiteBunnerText.getText());

    }

    @Test
    public void sendKeysInToTheSignUpFieldsTest() throws InterruptedException {
        // 1) find account button and click
        WebElement accountButton = driver.findElement(By.xpath("//button[contains(@id, 'navbarAccount')]"));
        accountButton.click();
        Thread.sleep(1000);

        // 2) In the opened drop down, find and click on the login button
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@id, 'navbarLoginButton')]"));
        loginButton.click();
        Thread.sleep(1000);


        // 3) Find button to create a new account and click on it
        WebElement notYetACustomer = driver.findElement(By.cssSelector("div#newCustomerLink"));
        notYetACustomer.click();
        Thread.sleep(1000);

        // 4) Find 'Email' text box and add some text
        WebElement regEmailTextBox = driver.findElement(By.xpath("//input[contains(@id, 'emailControl')]"));
        regEmailTextBox.sendKeys("testmail@yopmail.com");
        Thread.sleep(5000);

    }


    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
