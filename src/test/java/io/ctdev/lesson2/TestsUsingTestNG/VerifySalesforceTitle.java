package io.ctdev.lesson2.TestsUsingTestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class VerifySalesforceTitle {
    WebDriver driver;

    @BeforeClass
    public void goToTestWebPage() {
        driver = new ChromeDriver();
        driver.get("https://test.salesforce.com/");
    }

    @Test
    public void verifyPageTitle() throws InterruptedException {
        String expectedTitle = "Login | Salesforce";
        String actualTitle = driver.getTitle();
        Thread.sleep(3000);
        Assert.assertEquals(actualTitle, expectedTitle, "tab title is not the same as expected tab title");
    }

    @AfterClass
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }

}
