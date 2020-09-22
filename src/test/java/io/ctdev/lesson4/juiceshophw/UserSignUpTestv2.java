package io.ctdev.lesson4.juiceshophw;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserSignUpTestv2 extends BaseTestJuiceShop {
    RandomString rs = new RandomString(7);
    private String randomString = rs.nextString();
    private String email = "pzazu" + randomString + "@yopmail.com";
    private String password = "@z$rt&12!!azazaza";
    private List<WebElement> dropDownContent;
    private int iteration = 0;

    @Test
    public void verifyAbilityToCreateNewAccount() throws InterruptedException {

        System.out.println("Click on the 'Account' button");
        WebElement accountButton = driver.findElement(By.cssSelector("#navbarAccount"));
        accountButton.click();

        System.out.println("Click on the 'Login' button");
        WebElement loginButton = driver.findElement(By.cssSelector("#navbarLoginButton"));
        loginButton.click();

        System.out.println("Click on the 'Not yet a customer?' button");
        WebElement notYetACustomerButton = driver.findElement(By.cssSelector("div#newCustomerLink a"));
        notYetACustomerButton.click();

        System.out.println("Fill 'Password' Field");
        driver.findElement(By.cssSelector("#passwordControl")).sendKeys(password);

        System.out.println("Fill 'Repeat Password' Field");
        driver.findElement(By.cssSelector("#repeatPasswordControl")).sendKeys(password);

        //Here I add a cycle, cause selenium sometimes doesn't open the drop-down list
        do {
            driver.findElement(By.xpath("//mat-select[contains(@name, 'securityQuestion')]")).click();
            dropDownContent = driver.findElements(By.xpath("//mat-option"));
            iteration++;
            System.out.println("try");
            if (iteration == 20) {
                break;
            }
        } while (dropDownContent.size() < 1);

        System.out.println("Click on the option");
        waitUntilDisplayed(By.xpath("//mat-option[1]"), 2).click();

        System.out.println("Fill 'Answer' field with text");
        driver.findElement(By.cssSelector("#securityAnswerControl")).sendKeys("SecurityText:)");

        System.out.println("fill the 'Email' Field");
        driver.findElement(By.cssSelector("#emailControl")).sendKeys(email);

        System.out.println("Click 'Register' button");
        driver.findElement(By.cssSelector("#registerButton")).click();

        System.out.println("Find the Registration completed pop up");
        Assert.assertTrue(waitUntilDisplayed(By.xpath("//div[contains(@id, 'cdk-overlay')]//span[contains(text(), 'Registration completed')]"), 3).isDisplayed());

        //Print the User info that was registered
        System.out.println();
        System.out.println();
        System.out.println("Test passed! User created:");
        System.out.println("Email is: " + email);
        System.out.println("Password is: " + password);
    }
}
