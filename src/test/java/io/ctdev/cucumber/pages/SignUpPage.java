package io.ctdev.cucumber.pages;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SignUpPage extends AbstractPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private List<WebElement> dropDownContent;


    private By emailTextBoxElement = By.cssSelector("#emailControl");
    private By passwordTextBoxElement = By.cssSelector("#passwordControl");
    private By repeatPasswordTextBoxElemnt = By.cssSelector("#repeatPasswordControl");
    private By secQuestionDropDownElement = By.xpath("//mat-select[contains(@name, 'securityQuestion')]");
    private By secQuestionOption1Element = By.xpath("//mat-option[1]");
    private By secQuestionAnswerTextBoxElement = By.cssSelector("#securityAnswerControl");
    private By registerButtonElement = By.cssSelector("#registerButton");
    private By registerButtonInDisabledStateElement = By.xpath("//button[@id='registerButton' and @disabled='true']");
    private By emptyEmailFieldErrorMessage = By.xpath("//mat-error[contains(text(), 'Please provide an email')]");
    private By invalidEmailErrorMessage = By.xpath("//mat-error[contains(text(), 'Email address is not valid')]");
    private By alreadyExistsEmailErrorMessage = By.xpath("//div[contains(text(), 'Email must be unique')]");
    private By emptyPasswordFieldErrorMessage = By.xpath("//mat-error[contains(text(), 'Please provide a password. ')]");
    private By emptyRepeatPasswordFieldErrorMessage = By.xpath("//mat-error[contains(text(), 'Please repeat your password.')]");
    private By shortOrTooLongPasswordErrorMessage = By.xpath("//mat-error[contains(text(), 'Password must be 5-20')]");
    private By correctPasswordLengthMessage = By.xpath("//em[contains(text(), 'Password must be 5-20 characters long.')]");
    private By passwordsDoNotMatchErrorMessage = By.xpath("//mat-error[contains(text(), 'Passwords do not match')]");
    private By successfulRegistrationPopUpMessage = By.xpath("//div[contains(@id, 'cdk-overlay')]//span[contains(text(), 'Registration completed')]");

    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    @Step("Open SingUp page")
    public void openPage() {
        System.out.println("Open Signup Web Page");
        driver.get(TestConfig.cfg.juiceShopSignUpPage());
    }

    @Step("Fill 'Email' field")
    public void fillEmailField(String email) {
        System.out.println("Fill 'Email' field with text " + email);
        waitUntilDisplayed(emailTextBoxElement, 4).sendKeys(email);
    }

    @Step("Fill 'Password' field")
    public void fillPasswordField(String password) {
        System.out.println("Fill 'Password' Field with text " + password);
        driver.findElement(passwordTextBoxElement).sendKeys(password);
    }


    @Step("Fill 'Repeat Password' field")
    public void fillRepeatPasswordField(String password) {
        System.out.println("Fill 'Repeat Password' Field with text " + password);
        driver.findElement(repeatPasswordTextBoxElemnt).sendKeys(password);
    }


    public void clickOnTheSecurityQuestionDropDownElement() {
        driver.findElement(secQuestionDropDownElement).click();
    }

    @Step("Open 'Security Question' drop down list")
    public SignUpPage openSeqQuestionDropDownList() {
        System.out.println("Click on the 'Security Question' element to open drop down list");
        //Here I add a cycle, cause selenium sometimes doesn't open the drop-down list
        do {
            clickOnTheSecurityQuestionDropDownElement();
            dropDownContent = driver.findElements(secQuestionOption1Element);
            System.out.println("Check if DropDown Expanded");
        } while (dropDownContent.size() == 0);
        return this;
    }

    @Step("Choose one of the option from the drop down list")
    public void chooseOptionFromSecQuestionDropDownList() {
        System.out.println("Choose option from SecQuestion drop down list");
        driver.findElement(By.xpath("//mat-option[1]")).click();
    }

    @Step("Fill the 'Security Question' text field")
    public void fillSecurityQuestionTextBox(String securityQuestionAnswer) {
        driver.findElement(secQuestionAnswerTextBoxElement).sendKeys(securityQuestionAnswer);
    }

    @Step("Click on the register button")
    public void clickOnTheRegisterButton() {
        System.out.println("Click on the register button");
        driver.findElement(registerButtonElement).click();
    }

    @Step("Clear all fields that have entered text")
    public void clearAllFields() {
        waitUntilDisplayed(emailTextBoxElement, 2).clear();
        waitUntilDisplayed(passwordTextBoxElement, 2).clear();
        waitUntilDisplayed(repeatPasswordTextBoxElemnt, 2).clear();
        waitUntilDisplayed(secQuestionAnswerTextBoxElement, 2).clear();
    }

    @Step("Is error about blank email field displayed?")
    public Boolean isErrorWhenEmailFieldBlankDisplayed() {
        System.out.println("Is 'Please provide an email address' error displayed?");
        return waitUntilDisplayed((emptyEmailFieldErrorMessage), 4).isDisplayed();
    }

    @Step("Is error about invalid email format displayed?")
    public Boolean isErrorAboutInvalidEmailDisplayed() {
        System.out.println("Is 'Email address is not valid.' error is displayed?");
        return waitUntilDisplayed(invalidEmailErrorMessage, 5).isDisplayed();
    }

    @Step("Is error about already exists email displayed?")
    public Boolean isErrorAboutAlreadyExistsEmailDisplayed() {
        System.out.println("Is 'Email must be unique' error displayed?");
        return waitUntilDisplayed(alreadyExistsEmailErrorMessage, 10).isDisplayed();
    }

    @Step("Is error about empty 'Password' field displayed?")
    public Boolean isErrorWhenPasswordFieldEmptyDisplayed() {
        System.out.println("Is 'Please provide a password.' error displayed?");
        return waitUntilDisplayed(emptyPasswordFieldErrorMessage, 5).isDisplayed();
    }

    @Step("Is error about empty 'Repeat Password' field displayed?")
    public Boolean isErrorWhenRepeatPasswordFieldEmptyDisplayed() {
        System.out.println("Is 'Please repeat your password.' error displayed?");
        return waitUntilDisplayed(emptyRepeatPasswordFieldErrorMessage, 10).isDisplayed();
    }

    @Step("Is error about too short or too long password displayed?")
    public Boolean isErrorWhenPasswordTooShortOrTooLongDisplayed() {
        System.out.println("Is 'Password must be 5-20 characters long.' error displayed?");
        return waitUntilDisplayed(shortOrTooLongPasswordErrorMessage, 10).isDisplayed();
    }

    @Step("Is message about correct password length displayed?")
    public Boolean isMessageWhenPasswordHasCorrectLengthDisplayed() {
        System.out.println("Is message about correct password length displayed?");
        return waitUntilDisplayed(correctPasswordLengthMessage, 5).isDisplayed();
    }

    @Step("Is error message about do not match passwords displayed?")
    public Boolean isPasswordsDoNotMatchErrorDisplayed() {
        System.out.println("Is 'Passwords do not match' error displayed?");
        return waitUntilDisplayed(passwordsDoNotMatchErrorMessage, 10).isDisplayed();
    }

    @Step("Is 'Register' button in not active state?")
    public Boolean isRegisterButtonInNotActiveState() {
        System.out.println("Is Register button in not active state?");
        return waitUntilDisplayed(registerButtonInDisabledStateElement, 3).isDisplayed();
    }

    @Step("Is pop up about successfully registered user displayed?")
    public Boolean isRegistrationSuccessfulPopUpDisplayed() {
        System.out.println("Is 'Registration completed successfully. You can now log in.' pop up message displayed?");
        return waitUntilDisplayed(successfulRegistrationPopUpMessage, 3).isDisplayed();
    }

    @Step("Click in to the 'Password' text box")
    public void hoverInToThePasswordTextBox() {
        waitUntilDisplayed(passwordTextBoxElement, 3).click();
    }

    @Step("Click in to the 'Repeat Password' text box")
    public void hoverInToTheRepeatPasswordTextBox() {
        waitUntilDisplayed(repeatPasswordTextBoxElemnt, 3).click();
    }

    @Step("Click in to the 'Email' text box")
    public void hoverInToTheEmailTextBox() {
        waitUntilDisplayed(emailTextBoxElement, 5).click();
    }
}

