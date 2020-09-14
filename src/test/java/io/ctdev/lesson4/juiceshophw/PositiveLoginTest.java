package io.ctdev.lesson4.juiceshophw;

import io.ctdev.framework.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class PositiveLoginTest {
    WebDriver driver;
    private String email = "pzzzzazuliak@yopmail.com";
    private String password = "@z$rt&12!!azazaza";
    private String xPathToLoginVerefication = "//button/span[contains(text(), '" + email + "')]";

    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
        //Open Web Page
        driver.get(TestConfig.cfg.juiceShopProd());
        //Useing cookies adjustment, banner and cookies consent pop-ups will be closed
        driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
        driver.manage().addCookie(new Cookie("welcomebanner_status", "dismiss"));
        driver.navigate().refresh();
    }

    @Test
    public void verifyAbilityToLogInWithValidCreds() throws InterruptedException {
        openLoginPage();
        System.out.println("Fill 'Email' field with valid Email");
        fillEmailTextBox(email);
        System.out.println("Fill 'Password' field with valid Password");
        fillPasswordTextBox(password);
        clickLoginButton();
        Thread.sleep(2000);
        checkUserLoggedInSuccessfully();



    }

    public void openLoginPage() {
        System.out.println("Click on the 'Account' button");
        driver.findElement(By.cssSelector("#navbarAccount")).click();

        System.out.println("Click on the 'Login' button");
        driver.findElement(By.cssSelector("#navbarLoginButton")).click();
    }

    public void fillEmailTextBox(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void fillPasswordTextBox(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickLoginButton() {
        System.out.println("click 'Login' button");
        driver.findElement(By.id("loginButton")).click();
    }

    public void checkUserLoggedInSuccessfully() {
        System.out.println("Click on the 'Account' button");
        driver.findElement(By.cssSelector("#navbarAccount")).click();
        System.out.println("Check That user profile with Email " + email + " displayed in the list");
        Assert.assertTrue(driver.findElement(By.xpath(xPathToLoginVerefication)).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
