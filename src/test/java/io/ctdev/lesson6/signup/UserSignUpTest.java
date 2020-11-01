package io.ctdev.lesson6.signup;

import io.ctdev.framework.model.JuiceShopUser;
import io.ctdev.framework.model.JuiceShopUserBuilder;
import io.ctdev.framework.pages.signup.SignUpPage;
import io.ctdev.lesson6.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import net.bytebuddy.utility.RandomString;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

@Epic("SignUp/SignIn")
@Story("User Account creation")

public class UserSignUpTest extends BaseTest {
    private SignUpPage signUpPage;
    private RandomString randomString = new RandomString(7);
    private JuiceShopUser newUser;

    @BeforeClass
    public void setUP() {
        signUpPage = new SignUpPage(driver);
        signUpPage.openPage();
        signUpPage.closeWelcomeAndCookiesPopUps();
        waitForPageToLoad(driver);
        newUser = new JuiceShopUserBuilder().email("pzazu" + randomString.nextString() + "@yopmail.com").password("@z$rt&12!!azazaza").build();

    }

    @Test
    @Description("Verify that user can create new account")
    public void verifyAbilityToCreateNewAccount() {
        System.out.println();
        System.out.println("verifyAbilityToCreateNewAccount test");
        signUpPage.fillEmailField(newUser.getEmail());
        signUpPage.fillPasswordField(newUser.getPassword());
        signUpPage.fillRepeatPasswordField(newUser.getPassword());
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("security question answer :)");
        signUpPage.clickOnTheRegisterButton();
        Assert.assertTrue(signUpPage.isRegistrationSuccessfulPopUpDisplayed());
    }

}
