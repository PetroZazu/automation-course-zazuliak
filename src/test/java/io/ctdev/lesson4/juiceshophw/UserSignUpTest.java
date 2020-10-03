package io.ctdev.lesson4.juiceshophw;

import io.ctdev.lesson4.juiceshophw.BaseTestJuiceShop;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserSignUpTest extends BaseTestJuiceShop {
    private int iteration = 13;
    private String email = "pzzzzazuliak@yopmail.com";
    private String password = "@z$rt&12!!azazaza";
    private List<WebElement> errorsDisp;
    private List<WebElement> dropDownContent;
    private boolean error;


    @Test
    public void verifyAbilityToCreateNewAccount() {

        // open registration page
        System.out.println("1) Click on the 'Account' button");
        WebElement accountButton = driver.findElement(By.cssSelector("#navbarAccount"));
        accountButton.click();

        System.out.println("2) Click on the 'Login' button");
        WebElement loginButton = driver.findElement(By.cssSelector("#navbarLoginButton"));
        loginButton.click();

        System.out.println("3) Click on the 'Not yet a customer?' button");
        WebElement notYetACustomerButton = driver.findElement(By.cssSelector("div#newCustomerLink a"));
        notYetACustomerButton.click();

        // Enter data in the fields
        System.out.println("4) Fill 'Password' Field");
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password);

        System.out.println("5) Fill 'Repeat Password' Field");
        driver.findElement(By.cssSelector("#repeatPasswordControl")).sendKeys(password);

        //Here I add a cycle, cause selenium sometimes doesn't open the drop-down list
        do {
            driver.findElement(By.xpath("//mat-select[contains(@name, 'securityQuestion')]")).click();
            dropDownContent = driver.findElements(By.xpath("//mat-option"));
            System.out.println("try");
        } while (dropDownContent.size() < 1);

        System.out.println("Click on the option");
        waitUntilDisplayed(By.xpath("//mat-option[1]"), 2).click();

        System.out.println("8) Fill 'Answer' field with text");
        driver.findElement(By.cssSelector("#securityAnswerControl")).sendKeys("SecurityText:)");


        do {
            System.out.println("9) Clear and fill the 'Email' Field");
            driver.findElement(By.cssSelector("#emailControl")).clear();
            driver.findElement(By.cssSelector("#emailControl")).sendKeys(email);

            System.out.println("10) Click 'Register' button");
            driver.findElement(By.cssSelector("#registerButton")).click();

            try {
                if (waitUntilDisplayed(By.xpath("//div[contains(text(), 'Email must be unique')]"), 3).isDisplayed()) {
                    iteration++;
                    email = "pzzzzazuliak" + iteration + "@yopmail.com";
                    error = true;
                }

            } catch (TimeoutException ex) {
                error = false;
            }


        } while (error);

        //Now we need to verify that the success pop up message about creation of the new user appears
        System.out.println("11) Find the Registration completed pop up");
        Assert.assertTrue(waitUntilDisplayed(By.xpath("//div[contains(@id, 'cdk-overlay')]//span[contains(text(), 'Registration completed')]"), 3).isDisplayed());


        //Print the User info that was registered
        System.out.println();
        System.out.println();
        System.out.println("Test passed! User created:");
        System.out.println("Email is: " + email);
        System.out.println("Password is: " + password);
    }
}


