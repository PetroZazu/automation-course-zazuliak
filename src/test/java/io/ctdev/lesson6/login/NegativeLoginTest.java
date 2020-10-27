package io.ctdev.lesson6.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.model.JuiceShopUser;
import io.ctdev.framework.model.JuiceShopUserBuilder;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.lesson6.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

public class NegativeLoginTest extends BaseTest {

    private LoginPage loginPage;
    private JuiceShopUser User1ValidEmailInvalidPassword;
    private JuiceShopUser User2InvalidEmailValidPassword;

    @BeforeClass
    public void setUp() {
        User1ValidEmailInvalidPassword = new JuiceShopUserBuilder().email("pzzzzazuliak@yopmail.com").password("aswq1sf21d").build();
        User2InvalidEmailValidPassword = new JuiceShopUserBuilder().email("pzazul@yopmail.com").password("@z$rt&12!!azazaza").build();
        loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.closeWelcomeAndCookiesPopUps();
    }

    @AfterMethod
    public void afterMethod() {
        loginPage.clearEmailAndPasswordFields();
        System.out.println("Go to the login page again");
        driver.get(TestConfig.cfg.juiceShopLoginPage());
        waitForPageToLoad(driver);
    }

    @Test
    public void loginWhenInvalidPasswordValidEmail() {
        System.out.println();
        System.out.println("loginWhenInvalidPasswordValidEmail test");

        System.out.println("Fill valid email");
        loginPage.fillEmailTextBox(User1ValidEmailInvalidPassword.getEmail());

        System.out.println("Fill invalid password");
        loginPage.fillPasswordTextBox(User1ValidEmailInvalidPassword.getPassword());

        loginPage.clickOnSubmitLoginButton();

        System.out.println("Verify that Error about invalid Email/Password displayed");
        Assert.assertTrue(loginPage.isInvalidLoginOrPasswordErrorMessageDisplayed());
    }

    @Test
    public void loginWhenInvalidEmailValidPassword() {
        System.out.println();
        System.out.println("loginWhenInvalidEmailValidPassword test");

        System.out.println("Fill invalid email");
        loginPage.fillEmailTextBox(User2InvalidEmailValidPassword.getEmail());

        System.out.println("Fill valid password");
        loginPage.fillPasswordTextBox(User2InvalidEmailValidPassword.getPassword());

        loginPage.clickOnSubmitLoginButton();

        System.out.println("Verify that Error about invalid Email/Password displayed");
        Assert.assertTrue(loginPage.isInvalidLoginOrPasswordErrorMessageDisplayed());
    }

    @Test
    public void loginWhenEmptyPassword() {
        System.out.println();
        System.out.println("loginWhenEmptyPassword test");

        System.out.println("Fill valid email");
        loginPage.fillEmailTextBox(User1ValidEmailInvalidPassword.getEmail());

        loginPage.clickInAndClickOutPasswordTextBox();

        System.out.println("Verify that empty password field error appears");
        Assert.assertTrue(loginPage.isEmptyPasswordErrorDisplayed());

        System.out.println("Verify that Login button is in inactive state");
        Assert.assertTrue(loginPage.isSubmitButtonInactive());
    }

}
