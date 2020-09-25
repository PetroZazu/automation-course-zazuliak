package io.ctdev.preconditions.juiceshop;

import io.ctdev.lesson4.juiceshophw.BaseTestJuiceShop;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountActions {
    private static String email = "pzzzzazuliak@yopmail.com";
    private static String password = "@z$rt&12!!azazaza";

    public static void logInToTheAccount(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        System.out.println("LogIn to the account");
        //click Account Button
        driver.findElement(By.cssSelector("#navbarAccount")).click();
        //click Login Button
        driver.findElement(By.cssSelector("#navbarLoginButton")).click();
        //fill 'Email' field
        driver.findElement(By.id("email")).sendKeys(email);
        //fill 'Password' field
        driver.findElement(By.id("password")).sendKeys(password);
        //click 'Login' button
        driver.findElement(By.id("loginButton")).click();
        //check that user is really logged in
        BaseTestJuiceShop.waitUntilDisplayed(By.cssSelector("#navbarAccount"), 5).click();
        BaseTestJuiceShop.waitUntilDisplayed(By.xpath("//button/span[contains(text(), 'pzzzzazuliak@yopmail.com')]"), 5);
        driver.navigate().refresh();
    }


}
