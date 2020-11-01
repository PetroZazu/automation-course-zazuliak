package io.ctdev.lesson6.signup;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.signup.SignUpPage;
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
@Story("Password validation rules")

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
    @Description("Check that password fields are required")
    public void verifyPasswordFieldsIsRequired() {
        System.out.println();
        System.out.println("verifyPasswordFieldsIsRequired test");
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
    @Description("Verify too short password error")
    public void verifyTooShortPassword() {
        System.out.println();
        System.out.println("verifyTooShortPassword test");
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
    @Description("Verify too long password error")
    public void verifyTooLongPassword() {
        System.out.println();
        System.out.println("verifyTooLongPassword test");
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
    @Description("Verify ability to provide max allowed length password")
    public void verifyMaxAllowedPasswordLength() {
        System.out.println();
        System.out.println("verifyMaxAllowedPasswordLength test");
        signUpPage.fillEmailField(validEmailType);
        signUpPage.fillPasswordField(password20symbols);
        signUpPage.fillRepeatPasswordField(password20symbols);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isMessageWhenPasswordHasCorrectLengthDisplayed());
    }

    @Test
    @Description("Verify ability to provide min allowed length password")
    public void verifyMinAllowedPasswordLength() {
        System.out.println();
        System.out.println("verifyMinAllowedPasswordLength test");
        signUpPage.fillEmailField(validEmailType);
        signUpPage.fillPasswordField(password5symbols);
        signUpPage.fillRepeatPasswordField(password5symbols);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isMessageWhenPasswordHasCorrectLengthDisplayed());
    }

    @Test
    @Description("Verify inability to create account when passwords do not match")
    public void verifyErrorWhenPasswordsDoNotMatch() {
        System.out.println();
        System.out.println("verifyErrorWhenPasswordsDoNotMatch test");
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
