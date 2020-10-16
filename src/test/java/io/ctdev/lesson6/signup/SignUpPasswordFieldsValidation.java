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

public class SignUpPasswordFieldsValidation {
    private WebDriver driver = getDriver();
    private SignUpPage signUpPage = new SignUpPage(driver);
    private String validEmailType = "zazazaazazaza@yopmail.com";
    private String validPasswordType = "Azerty12!!";
    private String password4symbols = "1234";
    private String password21symbols = "123qwe123qwe123qwe123";
    private String password5symbols = "12345";
    private String password20symbols = "123qwe123qwe123qwe12";

    @BeforeClass
    public void setUP() {
        signUpPage.openPage();
        signUpPage.closeWelcomeAndCookiesPopUps();
       // waitForPageToLoad(driver);
    }

    @AfterMethod
    public void afterEachMethod() {
        driver.navigate().refresh();
        waitForPageToLoad(driver);
    }

    @Test
    public void verifyPasswordFieldsIsRequired() {
        signUpPage.fillAllFieldsExceptOfBothPasswordFields(validEmailType);
        Assert.assertTrue(signUpPage.isErrorWhenPasswordFieldEmptyDisplayed());
        Assert.assertTrue(signUpPage.isErrorWhenRepeatPasswordFieldEmptyDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

    @Test
    public void verifyTooShortPassword() {
        signUpPage.fillAllFieldsExceptOfBothPasswordFields(validEmailType);
        System.out.println("Fill 'Password' Field with password that has 4 symbols");
        signUpPage.fillPasswordField(password4symbols);
        Assert.assertTrue(signUpPage.isErrorWhenPasswordTooShortOrTooLongDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

    @Test
    public void verifyTooLongPassword() {
        signUpPage.fillAllFieldsExceptOfBothPasswordFields(validEmailType);
        System.out.println("Fill 'Password' Field with password that has 21 symbols");
        signUpPage.fillPasswordField(password21symbols);
        Assert.assertTrue(signUpPage.isErrorWhenPasswordTooShortOrTooLongDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

    @Test
    public void verifyMaxAllowedPasswordLength() {
        signUpPage.fillAllFieldsExceptOfBothPasswordFields(validEmailType);
        System.out.println("Fill 'Password' Field with password that has 20 symbols");
        signUpPage.fillPasswordField(password20symbols);
        Assert.assertTrue(signUpPage.isMessageWhenPasswordHasCorrectLengthDisplayed());
        signUpPage.isRegisterButtonInNotActiveState();
    }

    @Test
    public void verifyMinAllowedPasswordLength() {
        signUpPage.fillAllFieldsExceptOfBothPasswordFields(validEmailType);
        System.out.println("Fill 'Password' Field with password that has 5 symbols");
        signUpPage.fillPasswordField(password5symbols);
        Assert.assertTrue(signUpPage.isMessageWhenPasswordHasCorrectLengthDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

    @Test
    public void verifyErrorWhenPasswordsDoNotMatch() {
        signUpPage.fillAllFieldsExceptOfBothPasswordFields(validEmailType);
        signUpPage.fillPasswordField(password4symbols);
        signUpPage.fillRepeatPasswordField(password5symbols);
        Assert.assertTrue(signUpPage.isPasswordsDoNotMatchErrorDisplayed());
        Assert.assertTrue(signUpPage.isRegisterButtonInNotActiveState());
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }


}