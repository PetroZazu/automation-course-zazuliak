package io.ctdev.lesson6.signup;

import io.ctdev.framework.model.JuiceShopUser;
import io.ctdev.framework.model.JuiceShopUserBuilder;
import io.ctdev.framework.pages.signup.SignUpPage;
import io.ctdev.lesson6.BaseTest;
import net.bytebuddy.utility.RandomString;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

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

}
