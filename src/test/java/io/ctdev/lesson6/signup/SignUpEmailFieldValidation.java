package io.ctdev.lesson6.signup;

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
@Story("Email validation rules")

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
    @Description("Verify that error appears when email field blank")
    public void verifyErrorWhenEmailBlank() {
        System.out.println();
        System.out.println("verifyErrorWhenEmailBlank test");
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
    @Description("Verify that error appears when entered email is invalid")
    public void verifyErrorWhenEmailInvalid() {
        System.out.println();
        System.out.println("verifyErrorWhenEmailInvalid test");
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
    @Description("Verify that error appears when entered email is already registered")
    public void verifyErrorWhenEmailAlreadyExist() {
        System.out.println();
        System.out.println("verifyErrorWhenEmailAlreadyExist test");
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


