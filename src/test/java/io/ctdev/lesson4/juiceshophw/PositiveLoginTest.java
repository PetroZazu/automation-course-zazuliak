package io.ctdev.lesson4.juiceshophw;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveLoginTest extends BaseTestJuiceShop {
    private String email = "pzzzzazuliak@yopmail.com";
    private String password = "@z$rt&12!!azazaza";
    private String xPathToLoginVerefication = "//button/span[contains(text(), '" + email + "')]";

    @Test
    public void verifyAbilityToLogInWithValidCreds() {
        openLoginPage();
        System.out.println("Fill 'Email' field with valid Email");
        fillEmailTextBox(email);
        System.out.println("Fill 'Password' field with valid Password");
        fillPasswordTextBox(password);
        clickLoginButton();
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
        waitUntilDisplayed(By.cssSelector("#navbarAccount"), 5).click();
        System.out.println("Check That user profile with Email " + email + " displayed in the list");
        Assert.assertTrue(waitUntilDisplayed(By.xpath(xPathToLoginVerefication), 5).isDisplayed());
    }
}
