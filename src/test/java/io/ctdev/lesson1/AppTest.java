package io.ctdev.lesson1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AppTest {
    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://test.salesforce.com/");
        Thread.sleep(10000);
        driver.quit();
    }



}
