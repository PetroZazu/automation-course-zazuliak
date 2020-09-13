package io.ctdev.lesson2.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class VerifySalesforceTitle {
    WebDriver driver;
    ChromeOptions options;

    @BeforeSuite
    public void setGooglePreStartOptions() {
        options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);

    }

    @BeforeClass
    public void goToTestWebPage() {
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
