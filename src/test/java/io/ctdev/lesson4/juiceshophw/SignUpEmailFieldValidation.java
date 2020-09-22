package io.ctdev.lesson4.juiceshophw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class SignUpEmailFieldValidation extends BaseTestJuiceShop {
    private String invalidEmail = "zazazaazazaza%gmail.com";
    private String validEmail = "pzzzzazuliak@yopmail.com";
    private String password = "Azerty12!!";
    private List<WebElement> dropDownContent;

    @Test
    public void verifyErrorWhenEmailBlank() {
        openRegistrationPage();
        fillAllFieldsExceptOfEmail();
        checkThatRegisterButtonIsNotActive();
        checkErrorWhenEmailFieldBlank();
    }

    @Test
    public void verifyErrorWhenEmailInvalid() {
        openRegistrationPage();
        fillAllFieldsExceptOfEmail();
        fillEmailFieldWithInvalidEmail();
        checkErrorWhenEmailInvalid();
        checkThatRegisterButtonIsNotActive();
    }

    @Test
    public void verifyErrorWhenEmailAlreadyExist() {
        openRegistrationPage();
        fillAllFieldsExceptOfEmail();
        fillEmailFieldWithAlreadyExistUserEmail();
        clickOnTheRegisterButton();
        checkEmailAlreadyExistsErrorDisplayed();
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

    public void clickOnTheRegisterButton() {
        System.out.println("Click on the register button");
        driver.findElement(By.cssSelector("#registerButton")).click();
    }

    public void fillAllFieldsExceptOfEmail() {
        System.out.println("Click on the 'Email' Text box but do not send keys inside");
        driver.findElement(By.cssSelector("#emailControl")).click();

        System.out.println("Fill 'Password' Field");
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password);

        System.out.println("Fill 'Repeat Password' Field");
        driver.findElement(By.cssSelector("#repeatPasswordControl")).sendKeys(password);

        System.out.println("Click on the 'Security Question drop down list");
        //Here I add a cycle, cause selenium sometimes doesn't open the drop-down list
        do {
            driver.findElement(By.xpath("//mat-select[contains(@name, 'securityQuestion')]")).click();
            dropDownContent = driver.findElements(By.xpath("//mat-option"));
            System.out.println("try");
        } while (dropDownContent.size() < 1);

        //driver.findElement(By.xpath("//mat-select[contains(@name, 'securityQuestion')]")).click();

        System.out.println("Click on the option");
        waitUntilDisplayed(By.xpath("//mat-option[1]"), 2).click();

        System.out.println("Fill 'Answer' field with text");
        driver.findElement(By.cssSelector("#securityAnswerControl")).sendKeys("SecurityText:)");


    }

    public void fillEmailFieldWithInvalidEmail() {
        System.out.println("fill email field with invalid email");
        waitUntilDisplayed(By.cssSelector("#emailControl"), 5).sendKeys(invalidEmail);
    }

    public void fillEmailFieldWithAlreadyExistUserEmail() {
        System.out.println("fill email field with exist user email");
        waitUntilDisplayed(By.cssSelector("#emailControl"), 5).sendKeys(validEmail);
    }

    public void checkThatRegisterButtonIsNotActive() {
        System.out.println("Registration button is not active?: ");
        System.out.print(waitUntilDisplayed(By.xpath("//button[@id='registerButton' and @disabled='true']"), 5).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='registerButton' and @disabled='true']")).isDisplayed());
    }

    public void checkErrorWhenEmailFieldBlank() {
        System.out.println("'Please provide an email address' error is displayed: ");
        System.out.println(waitUntilDisplayed(By.xpath("//mat-error[contains(text(), 'Please provide an email')]"), 5).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please provide an email')]")).isDisplayed());
    }

    public void checkErrorWhenEmailInvalid() {
        System.out.println("'Email address is not valid.' error is displayed: ");
        System.out.println(waitUntilDisplayed(By.xpath("//mat-error[contains(text(), 'Email address is not valid')]"), 5).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Email address is not valid')]")).isDisplayed());
    }

    public void checkEmailAlreadyExistsErrorDisplayed() {
        System.out.println("Is error:'Email must be unique' displayed? ");
        System.out.println(waitUntilDisplayed(By.xpath("//div[contains(text(), 'Email must be unique')]"), 5).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Email must be unique')]")).isDisplayed());
    }

}


