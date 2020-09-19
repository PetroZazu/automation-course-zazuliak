package io.ctdev.lesson4.juiceshophw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class SignUpEmailFieldValidation extends BaseTestJuiceShop {
    private String invalidEmail = "zazazaazazaza%gmail.com";
    private String validEmail = "pzzzzazuliak@yopmail.com";
    private String password = "Azerty12!!";


   /* @Factory(dataProvider = "dataProvider")
    public JuiceShopSignUpEmailFieldValidation(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }*/

   /* @DataProvider
    public static Object[] dataProvider() {
        return new Object[]{"invalidemail#mail.com", "invalidemailmail.com","123123123123123@123123123"};
    }*/

    @Test
    public void verifyErrorWhenEmailBlank() throws InterruptedException {
        openRegistrationPage();
        fillAllFieldsExceptOfEmail();
        checkThatRegisterButtonIsNotActive();
        checkErrorWhenEmailFieldBlank();
    }

    @Test
    public void verifyErrorWhenEmailInvalid() throws InterruptedException {
        openRegistrationPage();
        fillAllFieldsExceptOfEmail();
        fillEmailFieldWithInvalidEmail();
        checkErrorWhenEmailInvalid();
        checkThatRegisterButtonIsNotActive();
    }

    @Test
    public void verifyErrorWhenEmailAlreadyExist() throws InterruptedException {
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

    public void fillAllFieldsExceptOfEmail() throws InterruptedException {
        System.out.println("Click on the 'Email' Text box but do not send keys inside");
        driver.findElement(By.cssSelector("#emailControl")).click();

        System.out.println("Fill 'Password' Field");
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password);

        System.out.println("Fill 'Repeat Password' Field");
        driver.findElement(By.cssSelector("#repeatPasswordControl")).sendKeys(password);

        Thread.sleep(1500);
        System.out.println("Click on the 'Security Question drop down list");
        driver.findElement(By.xpath("//mat-select")).click();

        System.out.println("Click on the option");
        driver.findElement(By.xpath("//mat-option")).click();

        System.out.println("Fill 'Answer' field with text");
        driver.findElement(By.cssSelector("#securityAnswerControl")).sendKeys("SecurityText:)");
    }

    public void fillEmailFieldWithInvalidEmail() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("fill email field with invalid email");
        driver.findElement(By.cssSelector("#emailControl")).sendKeys(invalidEmail);
    }

    public void fillEmailFieldWithAlreadyExistUserEmail() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("fill email field with exist user email");
        driver.findElement(By.cssSelector("#emailControl")).sendKeys(validEmail);
    }

    public void checkThatRegisterButtonIsNotActive() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Registration button is not active?: ");
        System.out.print(driver.findElement(By.xpath("//button[@id='registerButton' and @disabled='true']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='registerButton' and @disabled='true']")).isDisplayed());
    }

    public void checkErrorWhenEmailFieldBlank() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("'Please provide an email address' error is displayed: ");
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please provide an email')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Please provide an email')]")).isDisplayed());
    }

    public void checkErrorWhenEmailInvalid() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("'Email address is not valid.' error is displayed: ");
        System.out.println(driver.findElement(By.xpath("//mat-error[contains(text(), 'Email address is not valid')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//mat-error[contains(text(), 'Email address is not valid')]")).isDisplayed());
    }

    public void checkEmailAlreadyExistsErrorDisplayed() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Is error:'Email must be unique' displayed? ");
        System.out.println(driver.findElement(By.xpath("//div[contains(text(), 'Email must be unique')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(), 'Email must be unique')]")).isDisplayed());
    }

}


