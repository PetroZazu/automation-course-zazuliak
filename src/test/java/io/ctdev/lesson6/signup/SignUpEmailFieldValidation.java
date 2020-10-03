package io.ctdev.lesson6.signup;

import io.ctdev.framework.pages.signup.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;
import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

public class SignUpEmailFieldValidation {
    private WebDriver driver = getDriver();
    private SignUpPage signUpPage = new SignUpPage(driver);
    String invalidEmail = "zazazaazazaza%gmail.com";
    String validEmail = "pzzzzazuliak@yopmail.com";

    String password = "Azerty12!!";
    String seqQuestionAnswer = "Security? maybe no)";


    @BeforeClass
    public void setUP() {
        signUpPage.openPage();
        waitForPageToLoad(driver);
    }

    @AfterMethod
    public void afterEachMethod() {
        driver.navigate().refresh();
        waitForPageToLoad(driver);
    }

    @Test
    public void verifyErrorWhenEmailBlank() {
        signUpPage.fillAllFieldsExceptOfEmail(password, seqQuestionAnswer);
        Assert.assertTrue(signUpPage.isErrorWhenEmailFieldBlankDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }


    @Test
    public void verifyErrorWhenEmailInvalid() {
        signUpPage.fillAllFieldsExceptOfEmail(password, seqQuestionAnswer);
        signUpPage.fillEmailField(invalidEmail);
        signUpPage.isErrorAboutInvalidEmailDisplayed();
        signUpPage.isRegisterButtonInNotActiveState();
    }

    @Test
    public void verifyErrorWhenEmailAlreadyExist() {
        signUpPage.fillAllFieldsExceptOfEmail(password,seqQuestionAnswer);
        signUpPage.fillEmailField(validEmail);
        signUpPage.clickOnTheRegisterButton();
        signUpPage.isErrorAboutAlreadyExistsEmailDisplayed();
    }


    @AfterClass
    public void afterClass() {
        closeDriver();
    }

}


