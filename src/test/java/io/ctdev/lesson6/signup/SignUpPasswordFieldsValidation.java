package io.ctdev.lesson6.signup;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.signup.SignUpPage;
import io.ctdev.lesson6.BaseTest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

public class SignUpPasswordFieldsValidation extends BaseTest {
    private SignUpPage signUpPage;
    private String validEmailType = "zazazaazazaza@yopmail.com";
    private String password4symbols = "1234";
    private String password21symbols = "123qwe123qwe123qwe12313";
    private String password5symbols = "12345";
    private String password20symbols = "123qwe123qwe123qwe12";

    @BeforeClass
    public void setUP() {
        signUpPage = new SignUpPage(driver);
        signUpPage.openPage();
        signUpPage.closeWelcomeAndCookiesPopUps();
        // waitForPageToLoad(driver);
    }

    @AfterMethod
    public void afterEachMethod() {
        signUpPage.clearAllFields();
        driver.get(TestConfig.cfg.juiceShopSignUpPage());
        waitForPageToLoad(driver);
    }

    @Test
    public void verifyPasswordFieldsIsRequired() {
        signUpPage.fillEmailField(validEmailType);
        signUpPage.hoverInToThePasswordTextBox();
        signUpPage.hoverInToTheRepeatPasswordTextBox();
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isErrorWhenPasswordFieldEmptyDisplayed());
        Assert.assertTrue(signUpPage.isErrorWhenRepeatPasswordFieldEmptyDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

    @Test
    public void verifyTooShortPassword() {
        signUpPage.fillEmailField(validEmailType);
        signUpPage.fillPasswordField(password4symbols);
        signUpPage.fillRepeatPasswordField(password4symbols);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isErrorWhenPasswordTooShortOrTooLongDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

    @Test
    public void verifyTooLongPassword() {
        signUpPage.fillEmailField(validEmailType);
        signUpPage.fillPasswordField(password21symbols);
        signUpPage.fillRepeatPasswordField(password21symbols);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isErrorWhenPasswordTooShortOrTooLongDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

    @Test
    public void verifyMaxAllowedPasswordLength() {
        signUpPage.fillEmailField(validEmailType);
        signUpPage.fillPasswordField(password20symbols);
        signUpPage.fillRepeatPasswordField(password20symbols);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isMessageWhenPasswordHasCorrectLengthDisplayed());
    }

    @Test
    public void verifyMinAllowedPasswordLength() {
        signUpPage.fillEmailField(validEmailType);
        signUpPage.fillPasswordField(password5symbols);
        signUpPage.fillRepeatPasswordField(password5symbols);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isMessageWhenPasswordHasCorrectLengthDisplayed());
    }

    @Test
    public void verifyErrorWhenPasswordsDoNotMatch() {
        signUpPage.fillEmailField(validEmailType);
        signUpPage.fillPasswordField(password5symbols);
        signUpPage.fillRepeatPasswordField(password20symbols);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isPasswordsDoNotMatchErrorDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

}
