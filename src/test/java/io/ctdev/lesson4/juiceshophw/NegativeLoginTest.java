package io.ctdev.lesson4.juiceshophw;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTest extends BaseTestJuiceShop {
    private String validEmail = "pzzzzazuliak@yopmail.com";
    private String validPassword = "@z$rt&12!!azazaza";
    private String invalidEmail = "pzazul@yopmail.com";
    private String invalidPassword = "aswes123";


    @Test
    public void loginWhenInvalidPasswordValidEmail() {
        openLoginPage();
        System.out.println("Fill valid email");
        fillEmailTextBox(validEmail);
        System.out.println("Fill invalid password");
        fillPasswordTextBox(invalidPassword);
        clickLoginButton();
        checkErrorWhenPassordOrEmailInvalid();
    }

    @Test
    public void loginWhenInvalidEmailValidPassword() {
        openLoginPage();
        System.out.println("Fill invalid email");
        fillEmailTextBox(invalidEmail);
        System.out.println("Fill valid password");
        fillPasswordTextBox(validPassword);
        clickLoginButton();
        checkErrorWhenPassordOrEmailInvalid();
    }

    @Test
    public void loginWhenEmptyPassword(){
        openLoginPage();
        System.out.println("Fill valid email");
        fillEmailTextBox(validEmail);
        clickInAndClickOutPasswordTextBox();
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
        Assert.assertTrue(waitUntilDisplayed(By.xpath("//div[contains(text(), 'Invalid email or password')]"), 5).isDisplayed());
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
        Assert.assertTrue(waitUntilDisplayed(By.xpath("//mat-error[contains(text(), 'Please provide a password')]"), 5).isDisplayed());
    }

    public void checkThatLoginButtonInactive() {
        System.out.println("Verify that Login button is in inactive state");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='loginButton' and @disabled='true']")).isDisplayed());
    }

}