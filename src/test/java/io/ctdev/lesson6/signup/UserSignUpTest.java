package io.ctdev.lesson6.signup;

import io.ctdev.framework.model.JuiceShopUser;
import io.ctdev.framework.model.JuiceShopUserBuilder;
import io.ctdev.framework.pages.signup.SignUpPage;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;
import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

public class UserSignUpTest {
    private WebDriver driver = getDriver();
    private SignUpPage signUpPage = new SignUpPage(driver);
    private RandomString randomString = new RandomString(7);
    private JuiceShopUser newUser;

    @BeforeClass
    public void setUP() {
        signUpPage.openPage();
        signUpPage.closeWelcomeAndCookiesPopUps();
        waitForPageToLoad(driver);
        newUser = new JuiceShopUserBuilder().email("pzazu" + randomString.nextString() + "@yopmail.com").password("@z$rt&12!!azazaza").build();

    }

    @Test
    public void verifyAbilityToCreateNewAccount() {
        signUpPage.fillEmailField(newUser.getEmail());
        signUpPage.fillPasswordField(newUser.getPassword());
        signUpPage.fillRepeatPasswordField(newUser.getPassword());
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("security question answer :)");
        signUpPage.clickOnTheRegisterButton();
        Assert.assertTrue(signUpPage.isRegistrationSuccessfulPopUpDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
