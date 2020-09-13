package io.ctdev.lesson4;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class JuiceShopCookiesTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = getDriver();
    }

    @Test
    public void pageLoading() {
        driver.get("http://18.217.145.6");
        driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
        driver.navigate().refresh();
        driver.manage();
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
