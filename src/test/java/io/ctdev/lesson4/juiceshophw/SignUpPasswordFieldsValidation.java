package io.ctdev.lesson4.juiceshophw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpPasswordFieldsValidation extends BaseTestJuiceShop {

    private String email = "zazazaazazaza@yopmail.com";
    private String validPassword = "Azerty12!!";
    private String password4symbols = "1234";
    private String password21symbols = "123qwe123qwe123qwe123";
    private String password5symbols = "12345";
    private String password20symbols = "123qwe123qwe123qwe12";

    @Test
    public void verifyPasswordIsRequired() throws InterruptedException {
        System.out.println("verifyPasswordIsRequired() test");
        openRegistrationPage();
        fillAllFieldsExceptOfPasswords();
        checkErrorMessageWhenPasswordFieldBlank();
        checkThatRegisterButtonIsNotActive();
    }

    @Test
    public void verifyTooShortPassword() throws InterruptedException {
        System.out.println("verifyTooShortPassword() test");
        openRegistrationPage();
        fillAllFieldsExceptOfPasswords();
        System.out.println("Fill 'Password' Field with password that has 4 symbols");
        fillPasswordField(password4symbols);
        checkErrorMessageAboutWrongPasswordLength();
        checkThatRegisterButtonIsNotActive();
    }

    @Test
    public void verifyTooLongPassword() throws InterruptedException {
        System.out.println("verifyTooLongPassword() test");
        openRegistrationPage();
        fillAllFieldsExceptOfPasswords();
        System.out.println("Fill 'Password' Field with password that has 21 symbols");
        fillPasswordField(password21symbols);
        checkErrorMessageAboutWrongPasswordLength();
        checkThatRegisterButtonIsNotActive();
    }

    @Test
    public void verifyMaxAllowedPasswordLength() throws InterruptedException {
        System.out.println("verifyMaxAllowedPasswordLength() test");
        openRegistrationPage();
        fillAllFieldsExceptOfPasswords();
        System.out.println("Fill 'Password' Field with password that has 20 symbols");
        fillPasswordField(password20symbols);
        checkMessageWhenPasswordHasCorrectLength();
        checkThatRegisterButtonIsNotActive();
    }

    @Test
    public void verifyMinAllowedPasswordLength() throws InterruptedException {
        System.out.println("verifyMinAllowedPasswordLength() test");
        openRegistrationPage();
        fillAllFieldsExceptOfPasswords();
        System.out.println("Fill 'Password' Field with password that has 5 symbols");
        fillPasswordField(password5symbols);
        checkMessageWhenPasswordHasCorrectLength();
        checkThatRegisterButtonIsNotActive();
    }

    @Test
    public void verifyRepeatPasswordIsRequired() throws InterruptedException {
        System.out.println("verifyRepeatPasswordIsRequired() test");
        openRegistrationPage();
        fillAllFieldsExceptOfPasswords();
        System.out.println("Fill 'Password' Field with valid password");
        fillPasswordField(validPassword);
        checkErrorMessageWhenRepeatPasswordFieldBlank();
        checkThatRegisterButtonIsNotActive();
    }

    @Test
    public void verifyErrorWhenPasswordsDoNotMatch() throws InterruptedException {
        System.out.println("verifyErrorWhenPasswordsDoNotMatch() test");
        openRegistrationPage();
        fillAllFieldsExceptOfPasswords();
        System.out.println("Fill 'Password' Field with password:'Azerty12!!' ");
        fillPasswordField(validPassword);
        System.out.println("Fill 'Repeat Password' Field with password:'12345' ");
        fillRepeatPasswordField(password5symbols);
        checkErrorAppearsWhenPasswordsDoNotMatch();
        checkThatRegisterButtonIsNotActive();
    }

    public void fillAllFieldsExceptOfPasswords() throws InterruptedException {
        System.out.println("fill email field with valid email");
        driver.findElement(By.cssSelector("#emailControl")).sendKeys(email);
        Thread.sleep(1000);
        System.out.println("Click on the 'Security Question drop down list");
        driver.findElement(By.xpath("//mat-select")).click();
        System.out.println("Choose one of the options");
        driver.findElement(By.xpath("//mat-option")).click();
        System.out.println("Fill 'Answer' field with text");
        driver.findElement(By.cssSelector("#securityAnswerControl")).sendKeys("SecurityText:)");
    }


    public void fillPasswordField(String password) {
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password);
        justClickOutOfTheTextBox();
    }

    public void fillRepeatPasswordField(String password) {
        driver.findElement(By.cssSelector("#repeatPasswordControl")).sendKeys(password);
        justClickOutOfTheTextBox();
    }


    public void clearPasswordField() {
        System.out.println("Click in the password text box, clear text and click out the text box");
        driver.findElement(By.cssSelector("#passwordControl")).click();
        driver.findElement(By.cssSelector("#passwordControl")).clear();
        driver.findElement(By.xpath("//h1[contains(text(), 'User Registration')]")).click();
    }

    public void clearRepeatPasswordField() {
        System.out.println("Click in the Repeat Password text box, clear text and click out the text box");
        driver.findElement(By.cssSelector("#repeatPasswordControl")).click();
        driver.findElement(By.cssSelector("#repeatPasswordControl")).clear();
        driver.findElement(By.xpath("//h1[contains(text(), 'User Registration')]")).click();
    }

    public void checkErrorMessageWhenPasswordFieldBlank() {
        //to trigger error, we need to click in the password text box, leave it empty and click out
        clearPasswordField();
        System.out.println("Is error message about blank 'Password' field displayed?");
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please provide a password. ')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please provide a password. ')]")).isDisplayed());
    }

    public void checkErrorMessageWhenRepeatPasswordFieldBlank() throws InterruptedException {
        //to trigger error, we need to click in the password text box, leave it empty and click out
        clearRepeatPasswordField();
        System.out.println("Is error message about blank 'Repeat Password' field displayed?");
        Thread.sleep(1000);
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please repeat your password.')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please repeat your password.')]")).isDisplayed());
    }

    public void checkErrorMessageAboutWrongPasswordLength() {
        System.out.println("Is error message about wrong password length displayed?");
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Password must be 5-20 characters long.')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Password must be 5-20 characters long.')]")).isDisplayed());
    }

    public void checkMessageWhenPasswordHasCorrectLength() {
        System.out.println("Is message about correct password length displayed?");
        System.out.println(driver.findElement(By.xpath("//em[contains(text(), 'Password must be 5-20 characters long.')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//em[contains(text(), 'Password must be 5-20 characters long.')]")).isDisplayed());
    }

    public void checkErrorAppearsWhenPasswordsDoNotMatch() {
        System.out.println("Is error message about 'Passwords do not match' displayed?");
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Passwords do not match')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Passwords do not match')]")).isDisplayed());
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

    public void checkThatRegisterButtonIsNotActive() {
        System.out.println("Registration button is not active?: ");
        System.out.println(driver.findElement(By.xpath("//button[@id='registerButton' and @disabled='true']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='registerButton' and @disabled='true']")).isDisplayed());
    }

    public void justClickOutOfTheTextBox() {
        driver.findElement(By.xpath("//h1[contains(text(), 'User Registration')]")).click();
    }
}
