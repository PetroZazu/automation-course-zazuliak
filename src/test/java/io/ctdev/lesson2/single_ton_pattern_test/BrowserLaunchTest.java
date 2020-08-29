package io.ctdev.lesson2.single_ton_pattern_test;

import io.ctdev.framework.WebDriverSingleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.WebDriverSingleton.getDriver;

public class BrowserLaunchTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
    }

    @Test
    public void openWebPage() throws InterruptedException {
        driver.get("https://test.salesforce.com/");
        Thread.sleep(5000);
        Assert.assertEquals(driver.getTitle(), "Login | Salesforce");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


}
