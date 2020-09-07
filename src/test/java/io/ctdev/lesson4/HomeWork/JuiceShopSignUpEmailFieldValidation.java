package io.ctdev.lesson4.HomeWork;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class JuiceShopSignUpEmailFieldValidation {
    WebDriver driver;
    private String invalidEmail = "zazazaazazaza%gmail.com";
    private String password = "@z$rt&12!!azazaza";

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

   /* @Factory(dataProvider = "dataProvider")
    public JuiceShopSignUpEmailFieldValidation(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }*/

   /* @DataProvider
    public static Object[] dataProvider() {
        return new Object[]{"invalidemail#mail.com", "invalidemailmail.com","123123123123123@123123123"};
    }*/

    @Test
    public void verifyAbilityToCreateNewAccount() throws InterruptedException {
        openRegistrationPage();
        fillAllFieldsExceptOfEmail();
        verifyThatRegisterButtonIsNotActive();
        verifyErrorWhenEmailFieldBlank();
        fillEmailFieldWithInvalidEmail();
        verifyErrorWhenEmailInvalid();
    }

    public void openRegistrationPage() {
        System.out.println("Click on the 'Account' button");
        WebElement accountButton = driver.findElement(By.cssSelector("#navbarAccount"));
        accountButton.click();

        System.out.println("Click on the 'Login' button");
        WebElement loginButton = driver.findElement(By.cssSelector("#navbarLoginButton"));
        loginButton.click();

        System.out.println("Click on the 'Not yet a customer?' button");
        WebElement notYetACustomerButton = driver.findElement(By.cssSelector("div#newCustomerLink a"));
        notYetACustomerButton.click();
    }

    public void fillAllFieldsExceptOfEmail() throws InterruptedException {
        System.out.println("Click on the 'Email' Text box but do not send keys inside");
        driver.findElement(By.cssSelector("#emailControl")).click();

        System.out.println("Fill 'Password' Field");
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password);

        System.out.println("Fill 'Repeat Password' Field");
        driver.findElement(By.cssSelector("#repeatPasswordControl")).sendKeys(password);

        Thread.sleep(2000);
        System.out.println("Click on the 'Security Question drop down list");
        driver.findElement(By.xpath("//mat-select")).click();

        System.out.println("Click on the option");
        driver.findElement(By.xpath("//mat-option")).click();

        System.out.println("Fill 'Answer' field with text");
        driver.findElement(By.cssSelector("#securityAnswerControl")).sendKeys("SecurityText:)");
    }

    public void fillEmailFieldWithInvalidEmail() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("fill email field with invalid email");
        driver.findElement(By.cssSelector("#emailControl")).sendKeys(invalidEmail);
    }

    public void verifyThatRegisterButtonIsNotActive() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Registration button is not active?: ");
        System.out.print(driver.findElement(By.xpath("//button[@id='registerButton' and @disabled='true']")).isDisplayed());
    }

    public void verifyErrorWhenEmailFieldBlank() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("'Please provide an email address' error is displayed: ");
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please provide an email')]")).isDisplayed());
    }

    public void verifyErrorWhenEmailInvalid() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("'Email address is not valid.' error is displayed: ");
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Email address is not valid')]")).isDisplayed());

    }


    @AfterClass
    public void afterClass() {
        closeDriver();
    }


}


