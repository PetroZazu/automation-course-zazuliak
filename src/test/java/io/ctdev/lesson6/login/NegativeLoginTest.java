package io.ctdev.lesson6.login;

import io.ctdev.framework.model.JuiceShopUser;
import io.ctdev.framework.model.JuiceShopUserBuilder;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.lesson6.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

@Epic("SignUp/SignIn")
@Story("User Login")

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
        driver.navigate().refresh();
        waitForPageToLoad(driver);
    }

    @Test
    @Description("Verify inability to logIn with Invalid Password and Valid Email")
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
    @Description("Verify Inability to log in when Invalid Email but Valid password")
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
    @Description("Verify inability to log in with empty password field")
    public void loginWhenEmptyPassword() {
        System.out.println();
        System.out.println("loginWhenEmptyPassword test");

        System.out.println("Fill valid email");
        loginPage.fillEmailTextBox(User1ValidEmailInvalidPassword.getEmail());

        loginPage.clickInThePasswordField();
        loginPage.clickInTheEmailField();

        System.out.println("Verify that empty password field error appears");
        Assert.assertTrue(loginPage.isEmptyPasswordErrorDisplayed());

        System.out.println("Verify that Login button is in inactive state");
        Assert.assertTrue(loginPage.isSubmitButtonInactive());
    }

}
