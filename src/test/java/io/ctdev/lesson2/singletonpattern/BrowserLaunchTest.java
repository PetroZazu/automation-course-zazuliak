package io.ctdev.lesson2.singletonpattern;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

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
