package io.ctdev.lesson6.login;

import io.ctdev.framework.model.JuiceShopUser;
import io.ctdev.framework.model.JuiceShopUserBuilder;
import io.ctdev.framework.pages.login.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.*;


public class PositiveLoginTest {
    JuiceShopUser user;
    private WebDriver driver = getDriver();
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        loginPage = new LoginPage(driver);
        loginPage.openPage();
        user = new JuiceShopUserBuilder().email("pzzzzazuliak@yopmail.com").password("@z$rt&12!!azazaza").build();
    }

    @Test
    public void verifyAbilityToLogInWithValidCreds() {

        loginPage.fillEmailTextBox(user.getEmail());

        loginPage.fillPasswordTextBox(user.getPassword());

        loginPage.clickOnSubmitLoginButton();

        loginPage.clickOnAccountButton();


        System.out.println("Check That user profile with Email " + user.getEmail() + " displayed in the list");
        Assert.assertEquals(loginPage.getCurrentLoggedInUserEmail(), user.getEmail(), "Logged in user email isn't equal to: " + user.getEmail());
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }


}

