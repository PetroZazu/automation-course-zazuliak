package io.ctdev.framework.pages.signup;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
    private By shortOrTooLongPasswordErrorMessage = By.xpath("//mat-error[contains(text(), 'Password must be 5-20 characters long.')]");
    private By correctPasswordLengthMessage = By.xpath("//em[contains(text(), 'Password must be 5-20 characters long.')]");
    private By passwordsDoNotMatchErrorMessage = By.xpath("//mat-error[contains(text(), 'Passwords do not match')]");
    private By successfulRegistrationPopUpMessage = By.xpath("//div[contains(@id, 'cdk-overlay')]//span[contains(text(), 'Registration completed')]");

    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    public void openPage() {
        System.out.println("Open Signup Web Page");
        driver.get(TestConfig.cfg.juiceShopSignUpPage());
    }

    public void fillEmailField(String email) {
        waitUntilDisplayed(emailTextBoxElement, 4).sendKeys(email);
    }


    public void fillPasswordField(String password) {
        System.out.println("Fill 'Password' Field");
        driver.findElement(passwordTextBoxElement).sendKeys(password);
    }


    public void fillRepeatPasswordField(String password) {
        System.out.println("Fill 'Repeat Password' Field");
        driver.findElement(repeatPasswordTextBoxElemnt).sendKeys(password);
    }


    public void clickOnTheSecurityQuestionDropDownElement() {
        driver.findElement(secQuestionDropDownElement).click();
    }

    public void openSeqQuestionDropDownList() {
        System.out.println("Click on the 'Security Question' element to open drop down list");
        //Here I add a cycle, cause selenium sometimes doesn't open the drop-down list
        do {
            clickOnTheSecurityQuestionDropDownElement();
            dropDownContent = driver.findElements(secQuestionOption1Element);
            System.out.println("Check if DropDown Expanded");
        } while (dropDownContent.size() == 0);
    }

    public void chooseOptionFromSecQuestionDropDownList() {
        System.out.println("Choose option from SecQuestion drop down list");
        driver.findElement(By.xpath("//mat-option[1]")).click();
    }

    public void fillSecurityQuestionTextBox(String securityQuestionAnswer) {
        driver.findElement(secQuestionAnswerTextBoxElement).sendKeys(securityQuestionAnswer);
    }

    public void clickOnTheRegisterButton() {
        System.out.println("Click on the register button");
        driver.findElement(registerButtonElement).click();
    }

    public void fillAllFieldsExceptOfEmail(String password) {
        System.out.println("Click on the 'Email' Text box but do not send keys inside");
        //clickEmailTextBox();
        waitUntilDisplayed(emailTextBoxElement, 2).sendKeys(Keys.TAB);

        fillPasswordField(password);

        fillRepeatPasswordField(password);

        openSeqQuestionDropDownList();

        chooseOptionFromSecQuestionDropDownList();

        System.out.println("Fill 'Answer' field with text");
        fillSecurityQuestionTextBox("Security question text :)");

    }


    public void fillAllFieldsExceptOfBothPasswordFields(String email) {
        System.out.println("Enter email: '" + email + "' in to the 'Email' text box");
        fillEmailField(email);

        waitUntilDisplayed(passwordTextBoxElement, 2).sendKeys(Keys.TAB);

        System.out.println("Skip filling 'Repeat Password' Field");
        waitUntilDisplayed(repeatPasswordTextBoxElemnt, 2).sendKeys(Keys.TAB);

        openSeqQuestionDropDownList();
        chooseOptionFromSecQuestionDropDownList();

        System.out.println("Fill 'Answer' field with text");
        fillSecurityQuestionTextBox("Security question text :)");

    }

    public Boolean isErrorWhenEmailFieldBlankDisplayed() {
        System.out.println("Is 'Please provide an email address' error displayed?");
        return waitUntilDisplayed((emptyEmailFieldErrorMessage), 4).isDisplayed();
    }

    public Boolean isErrorAboutInvalidEmailDisplayed() {
        System.out.println("Is 'Email address is not valid.' error is displayed?");
        return driver.findElement(invalidEmailErrorMessage).isDisplayed();
    }

    public Boolean isErrorAboutAlreadyExistsEmailDisplayed() {
        System.out.println("Is 'Email must be unique' error displayed?");
        return waitUntilDisplayed(alreadyExistsEmailErrorMessage, 10).isDisplayed();
    }

    public Boolean isErrorWhenPasswordFieldEmptyDisplayed() {
        System.out.println("Is 'Please provide a password.' error displayed?");
        return waitUntilDisplayed(emptyPasswordFieldErrorMessage, 10).isDisplayed();
    }

    public Boolean isErrorWhenRepeatPasswordFieldEmptyDisplayed() {
        System.out.println("Is 'Please repeat your password.' error displayed?");
        return waitUntilDisplayed(emptyRepeatPasswordFieldErrorMessage, 10).isDisplayed();
    }

    public Boolean isErrorWhenPasswordTooShortOrTooLongDisplayed() {
        System.out.println("Is 'Password must be 5-20 characters long.' error displayed?");
        return waitUntilDisplayed(shortOrTooLongPasswordErrorMessage, 10).isDisplayed();
    }

    public Boolean isMessageWhenPasswordHasCorrectLengthDisplayed() {
        System.out.println("Is message about correct password length displayed?");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(correctPasswordLengthMessage)).isDisplayed();
    }

    public Boolean isPasswordsDoNotMatchErrorDisplayed() {
        System.out.println("Is 'Passwords do not match' error displayed?");
        return waitUntilDisplayed(passwordsDoNotMatchErrorMessage, 10).isDisplayed();
    }

    public Boolean isRegisterButtonInNotActiveState() {
        System.out.println("Is Register button in not active state?");
        return waitUntilDisplayed(registerButtonInDisabledStateElement, 3).isDisplayed();
    }

    public Boolean isRegistrationSuccessfulPopUpDisplayed() {
        System.out.println("Is 'Registration completed successfully. You can now log in.' pop up message displayed?");
        return waitUntilDisplayed(successfulRegistrationPopUpMessage, 3).isDisplayed();
    }


}
