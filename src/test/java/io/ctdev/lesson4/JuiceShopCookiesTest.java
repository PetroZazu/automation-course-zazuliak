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
        driver.manage().addCookie(new Cookie("io", "DfNuP6iQjGLdsWrmAAD0"));
        driver.manage().addCookie(new Cookie("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdGF0dXMiOiJzdWNjZXNzIiwiZGF0YSI6eyJpZCI6MjEsInVzZXJuYW1lIjoiIiwiZW1haWwiOiJwZXRyb3Rlc3QxQHlvcG1haWwuY29tIiwicGFzc3dvcmQiOiI1ODEyZGQ1MzVhNmQ2ODg2ZWRjNjE0ZDkzZjE4ZjJlZiIsInJvbGUiOiJjdXN0b21lciIsImRlbHV4ZVRva2VuIjoiIiwibGFzdExvZ2luSXAiOiIwLjAuMC4wIiwicHJvZmlsZUltYWdlIjoiL2Fzc2V0cy9wdWJsaWMvaW1hZ2VzL3VwbG9hZHMvZGVmYXVsdC5zdmciLCJ0b3RwU2VjcmV0IjoiIiwiaXNBY3RpdmUiOnRydWUsImNyZWF0ZWRBdCI6IjIwMjAtMDktMDUgMjA6MDg6NTIuOTUzICswMDowMCIsInVwZGF0ZWRBdCI6IjIwMjAtMDktMDUgMjA6MDg6NTIuOTUzICswMDowMCIsImRlbGV0ZWRBdCI6bnVsbH0sImlhdCI6MTU5OTMzNjU0NSwiZXhwIjoxNTk5MzU0NTQ1fQ.PDOWPrX0U79NLuCp5ZZ8PBoDriNjcRC683PLiEMrwVR_xx-EgHwBEg7LIdwZCrynwP6_kmlT8zbUkGcu9-PpUIzeL6vCfes9TclM3qzzuhMm8NMuytwLVv4AOtFsMaoTGdKDu-FnV119HLpWYz1BQYtbsVoK0PxrmB61TUpmAjU"));
        driver.navigate().refresh();
        driver.manage();
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
