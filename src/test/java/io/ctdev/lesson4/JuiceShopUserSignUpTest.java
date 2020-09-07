package io.ctdev.lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class JuiceShopUserSignUpTest {
    WebDriver driver;
    private int iteration;
    private String email;
    private String password = "@z$rt&12!!azazaza";
    private List<WebElement> errorsDisp;

    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
        //Open Web Page
        driver.get("http://18.217.145.6");
        //Useing cookies adjustment, banner and cookies consent pop-ups will be closed
        driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
        driver.manage().addCookie(new Cookie("welcomebanner_status", "dismiss"));
        driver.navigate().refresh();
    }

    @Test
    public void verifyAbilityToCreateNewAccount() throws InterruptedException {
        System.out.println("1) Click on the 'Account' button");
        WebElement accountButton = driver.findElement(By.cssSelector("#navbarAccount"));
        accountButton.click();

        System.out.println("2) Click on the 'Login' button");
        WebElement loginButton = driver.findElement(By.cssSelector("#navbarLoginButton"));
        loginButton.click();

        System.out.println("3) Click on the 'Not yet a customer?' button");
        WebElement notYetACustomerButton = driver.findElement(By.cssSelector("div#newCustomerLink a"));
        notYetACustomerButton.click();

        System.out.println("4) Fill 'Password' Field");
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password);

        System.out.println("5) Fill 'Repeat Password' Field");
        driver.findElement(By.cssSelector("#repeatPasswordControl")).sendKeys(password);

        Thread.sleep(2000);
        System.out.println("6) Click on the 'Security Question drop down list");
        driver.findElement(By.xpath("//mat-select")).click();


        System.out.println("7) Click on the option");
        driver.findElement(By.xpath("//mat-option")).click();

        System.out.println("8) Fill 'Answer' field with text");
        driver.findElement(By.cssSelector("#securityAnswerControl")).sendKeys("SecurityText:)");


        do {
            iteration++;
            email = "pzzzzazuliak" + iteration + "@yopmail.com";

            System.out.println("9) Clear and fill the 'Email' Field");
            driver.findElement(By.cssSelector("#emailControl")).clear();
            driver.findElement(By.cssSelector("#emailControl")).sendKeys(email);

            System.out.println("10) Click 'Register' button");
            driver.findElement(By.cssSelector("#registerButton")).click();

            //Let's find the error message, if message appears, errorDisp will be == 1
            Thread.sleep(1000);
            errorsDisp = driver.findElements(By.xpath("//div[contains(text(), 'Email must be unique')]"));

            //If error about existing email appears, we will ++int number in the email and will try again
        } while (errorsDisp.size() >= 1);

        //Now we need to verify that the success pop up message about creation of the new user appears
        System.out.println("11) Find the Registration completed pop up");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@id, 'cdk-overlay')]//span[contains(text(), 'Registration completed')]")).isDisplayed());


        //Print the User info that was registered
        System.out.println();
        System.out.println();
        System.out.println("Test passed! User created:");
        System.out.println("Email is: " + email);
        System.out.println("Password is: " + password);
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }


}


