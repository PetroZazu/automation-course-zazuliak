package io.ctdev.lesson4.juiceshophw;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class SignUpPasswordFieldsValidation {

    WebDriver driver;

    private String invalidEmail = "zazazaazazaza@yopmail.com";
    private String password4symbols = "1234";
    private String password21symbols = "123qwe123qwe123qwe123";
    private String password5symbols = "12345";
    private String password20symbols = "123qwe123qwe123qwe12";

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
    public void invalidPasswordOnSignUpPageTest() throws InterruptedException {
        openRegistrationPage();
        fillAllFieldsExceptOfPassword();
        verifyThatRegisterButtonIsNotActive();
        clearPasswordField();
        verifyErrorMessageWhenPasswordFieldBlank();

        //next test is related to boundary value test technique:
        fillPasswordFieldWithTooShortPassword();
        verifyErrorMessageAboutWrongPasswordLength();

        fillPasswordFieldWithTooLongPassword();
        verifyErrorMessageAboutWrongPasswordLength();

        fillPasswordFieldWithMaxLengthPassword();
        verifyMessageWhenPasswordHasCorrectLength();

        fillPasswordFieldWithMinLengthPassword();
        verifyMessageWhenPasswordHasCorrectLength();

        

    }

    public void fillAllFieldsExceptOfPassword() throws InterruptedException {
        System.out.println("fill email field with valid email");
        driver.findElement(By.cssSelector("#emailControl")).sendKeys(invalidEmail);

        Thread.sleep(1000);
        System.out.println("Click on the 'Security Question drop down list");
        driver.findElement(By.xpath("//mat-select")).click();

        System.out.println("Click on the option");
        driver.findElement(By.xpath("//mat-option")).click();

        System.out.println("Fill 'Answer' field with text");
        driver.findElement(By.cssSelector("#securityAnswerControl")).sendKeys("SecurityText:)");
    }

    public void fillPasswordFieldWithTooShortPassword() {
        System.out.println("Fill 'Password' Field with password that has 4 symbols");
        clearPasswordField();
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password4symbols);
        //just click out of the text box
        driver.findElement(By.xpath("//h1[contains(text(), 'User Registration')]")).click();
    }

    public void fillPasswordFieldWithTooLongPassword() {
        System.out.println("Fill 'Password' Field with password that has 21 symbols");
        clearPasswordField();
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password21symbols);
        //just click out of the text box
        driver.findElement(By.xpath("//h1[contains(text(), 'User Registration')]")).click();
    }

    public void fillPasswordFieldWithMaxLengthPassword() {
        System.out.println("Fill 'Password' Field with password that has 20 symbols");
        clearPasswordField();
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password20symbols);
        //just click out of the text box
        driver.findElement(By.xpath("//h1[contains(text(), 'User Registration')]")).click();
    }

    public void fillPasswordFieldWithMinLengthPassword() {
        System.out.println("Fill 'Password' Field with password that has 5 symbols");
        clearPasswordField();
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password5symbols);
        //just click out of the text box
        driver.findElement(By.xpath("//h1[contains(text(), 'User Registration')]")).click();
    }

    public void clearPasswordField() {
        driver.findElement(By.cssSelector("#passwordControl")).click();
        driver.findElement(By.cssSelector("#passwordControl")).clear();
        driver.findElement(By.xpath("//h1[contains(text(), 'User Registration')]")).click();

    }

    public void verifyErrorMessageWhenPasswordFieldBlank() throws InterruptedException {
        System.out.println("Is error message about blank 'Password' field displayed?");
        clearPasswordField();
        Thread.sleep(1000);
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please provide a password. ')]")).isDisplayed());
    }

    public void verifyErrorMessageWhenRepeatPasswordFieldBlank() {
        System.out.println("Is error message about blank 'Repeat Password' field displayed?");
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please repeat your password.')]")).isDisplayed());
    }

    public void verifyErrorMessageAboutWrongPasswordLength() throws InterruptedException {
        System.out.println("Is error message about wrong password length displayed?");
        Thread.sleep(1000);
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Password must be 5-20 characters long.')]")).isDisplayed());
    }

    public void verifyMessageWhenPasswordHasCorrectLength() throws InterruptedException {
        System.out.println("Is message about correct password length displayed?");
        Thread.sleep(1000);
        System.out.println(driver.findElement(By.xpath("//em[contains(text(), 'Password must be 5-20 characters long.')]")).isDisplayed());
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

    public void verifyThatRegisterButtonIsNotActive() throws InterruptedException {
        System.out.println("Registration button is not active?: ");
        Thread.sleep(1000);
        System.out.println(driver.findElement(By.xpath("//button[@id='registerButton' and @disabled='true']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }

}
