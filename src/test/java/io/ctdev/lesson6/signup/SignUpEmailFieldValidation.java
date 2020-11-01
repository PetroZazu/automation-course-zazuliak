package io.ctdev.lesson6.signup;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.signup.SignUpPage;
import io.ctdev.lesson6.BaseTest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

public class SignUpEmailFieldValidation extends BaseTest {
    private SignUpPage signUpPage;
    String invalidEmail = "zazazaazazaza%gmail.com";
    String validEmail = "pzzzzazuliak@yopmail.com";
    String password = "Azerty12!!";

    @BeforeClass
    public void setUP() {
        signUpPage = new SignUpPage(driver);
        signUpPage.openPage();
        signUpPage.closeWelcomeAndCookiesPopUps();
        waitForPageToLoad(driver);
    }

    @AfterMethod
    public void afterEachMethod() {
        signUpPage.clearAllFields();
        driver.navigate().refresh();
        waitForPageToLoad(driver);
    }

    @Test
    public void verifyErrorWhenEmailBlank() {
        signUpPage.hoverInToTheEmailTextBox();
        signUpPage.fillPasswordField(password);
        signUpPage.fillRepeatPasswordField(password);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        Assert.assertTrue(signUpPage.isErrorWhenEmailFieldBlankDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }


    @Test
    public void verifyErrorWhenEmailInvalid() {
        signUpPage.fillEmailField(invalidEmail);
        signUpPage.fillPasswordField(password);
        signUpPage.fillRepeatPasswordField(password);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        signUpPage.isErrorAboutInvalidEmailDisplayed();
        signUpPage.isRegisterButtonInNotActiveState();
    }

    @Test
    public void verifyErrorWhenEmailAlreadyExist() {
        signUpPage.fillEmailField(validEmail);
        signUpPage.fillPasswordField(password);
        signUpPage.fillRepeatPasswordField(password);
        signUpPage.openSeqQuestionDropDownList();
        signUpPage.chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox("Abrakadabra");
        signUpPage.clickOnTheRegisterButton();
        signUpPage.isErrorAboutAlreadyExistsEmailDisplayed();
    }

}


