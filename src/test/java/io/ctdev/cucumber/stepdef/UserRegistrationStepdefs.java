package io.ctdev.cucumber.stepdef;

import io.ctdev.cucumber.pages.SignUpPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class UserRegistrationStepdefs {
    private SignUpPage signUpPage = new SignUpPage(getDriver());
    private RandomString randomString = new RandomString(7);
    private String email = "pZazuNew4eburek" + randomString.nextString() + "@gmail.com";

    @When("user opens signIn page")
    public void userOpensSignInPage() {
        signUpPage.openPage();
    }

    @When("User provides uniqueRandomEmail and password {string} and repeatPassword {string} and securityQuestion {string}")
    public void userProvidesEmailPasswordRepeatPasswordSecurityQuestion(String password, String repeatPassword, String secQuestionAnswer) {
        signUpPage.fillEmailField(this.email);
        signUpPage.fillPasswordField(password);
        signUpPage.fillRepeatPasswordField(repeatPassword);
        signUpPage.openSeqQuestionDropDownList().chooseOptionFromSecQuestionDropDownList();
        signUpPage.fillSecurityQuestionTextBox(secQuestionAnswer);

    }

    @When("User clicks on the Submit button")
    public void userClicksOnTheSubmitButton() {
        signUpPage.clickOnTheRegisterButton();
    }

    @Then("User should create new account for himself")
    public void userShouldCreateNewAccountForHimself() {
        Assert.assertTrue(signUpPage.isRegistrationSuccessfulPopUpDisplayed());
    }
}
