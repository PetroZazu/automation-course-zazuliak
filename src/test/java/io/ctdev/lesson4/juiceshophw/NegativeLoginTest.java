package io.ctdev.lesson4.juiceshophw;

import io.ctdev.framework.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class NegativeLoginTest {
    WebDriver driver;
    private String validEmail = "pzzzzazuliak@yopmail.com";
    private String validPassword = "@z$rt&12!!azazaza";
    private String invalidEmail = "pzazul@yopmail.com";
    private String invalidPassword = "aswes123";


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
    public void loginWhenInvalidPasswordValidEmail() throws InterruptedException {
        openLoginPage();
        System.out.println("Fill valid email");
        fillEmailTextBox(validEmail);
        System.out.println("Fill invalid password");
        fillPasswordTextBox(invalidPassword);
        clickLoginButton();
        Thread.sleep(1000);
        checkErrorWhenPassordOrEmailInvalid();
    }

    @Test
    public void loginWhenInvalidEmailValidPassword() throws InterruptedException {
        openLoginPage();
        System.out.println("Fill invalid email");
        fillEmailTextBox(invalidEmail);
        System.out.println("Fill valid password");
        fillPasswordTextBox(validPassword);
        clickLoginButton();
        Thread.sleep(1000);
        checkErrorWhenPassordOrEmailInvalid();
    }

    @Test
    public void loginWhenEmptyPassword() throws InterruptedException {
        openLoginPage();
        System.out.println("Fill valid email");
        fillEmailTextBox(validEmail);
        clickInAndClickOutPasswordTextBox();
        Thread.sleep(1000);
        checkIfEmptyPasswordFieldErrorAppears();
        checkThatLoginButtonInactive();
    }


    public void clickLoginButton() {
        System.out.println("click 'Login' button");
        driver.findElement(By.id("loginButton")).click();
    }

    public void fillEmailTextBox(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void fillPasswordTextBox(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void checkErrorWhenPassordOrEmailInvalid() {
        System.out.println("Verify that Error about invalid Email/Password displayed");
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Invalid email or password')]")).isDisplayed());
    }

    public void openLoginPage() {
        System.out.println("Click on the 'Account' button");
        driver.findElement(By.cssSelector("#navbarAccount")).click();

        System.out.println("Click on the 'Login' button");
        driver.findElement(By.cssSelector("#navbarLoginButton")).click();

        driver.navigate().refresh();
    }

    public void clickInAndClickOutPasswordTextBox() {
        driver.findElement(By.id("password")).click();
        driver.findElement(By.xpath("//h1[text()='Login']")).click();
    }

    public void checkIfEmptyPasswordFieldErrorAppears() {
        System.out.println("Verify that empty password field error appears");
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please provide a password')]")).isDisplayed());
    }

    public void checkThatLoginButtonInactive() {
        System.out.println("Verify that Login button is in inactive state");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='loginButton' and @disabled='true']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
