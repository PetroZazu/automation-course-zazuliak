package io.ctdev.framework.pages.signup;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private By emptyEmailFieldErrorElement = By.xpath("//mat-error[contains(text(), 'Please provide an email')]");
    private By invalidEmailErrorElement = By.xpath("//mat-error[contains(text(), 'Email address is not valid')]");
    private By alreadyExistsEmailErrorElement = By.xpath("//div[contains(text(), 'Email must be unique')]");

    public SignUpPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    public void openPage() {
        System.out.println("Open Signup Web Page");
        driver.get(TestConfig.cfg.juiceShopSignUpPage());
        System.out.println("Using cookies adjustment, banner and cookies consent pop-ups will be closed");
        driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
        driver.manage().addCookie(new Cookie("welcomebanner_status", "dismiss"));
        driver.navigate().refresh();
    }

    public void fillEmailField(String email) {
        waitUntilDisplayed(emailTextBoxElement, 4).sendKeys(email);
    }

    public void fillPasswordField(String password) {
        driver.findElement(passwordTextBoxElement).sendKeys(password);
    }

    public void fillRepeatPasswordField(String password) {
        driver.findElement(repeatPasswordTextBoxElemnt).sendKeys(password);
    }

    public void clickEmailTextBox() {
        waitUntilDisplayed(emailTextBoxElement, 5).click();
    }


    public void clickOnTheSequrityQuestionDropDownElement() {
        driver.findElement(secQuestionDropDownElement).click();
    }

    public void openSeqQuestionDropDownList() {
        //Here I add a cycle, cause selenium sometimes doesn't open the drop-down list
        do {
            clickOnTheSequrityQuestionDropDownElement();
            dropDownContent = driver.findElements(secQuestionOption1Element);
            System.out.println("Check if DropDown Expanded");
        } while (dropDownContent.size() == 0);
    }

    public void chooseOptionFromSecQuestionDropDownList() {
        driver.findElement(By.xpath("//mat-option[1]")).click();
    }

    public void fillSecurityQuestionTextBox(String securityQuestionAnswer) {
        driver.findElement(secQuestionAnswerTextBoxElement).sendKeys(securityQuestionAnswer);
    }

    public void clickOnTheRegisterButton() {
        System.out.println("Click on the register button");
        driver.findElement(registerButtonElement).click();
    }

    public void fillAllFieldsExceptOfEmail(String password, String securityQuestionAnswer) {
        System.out.println("Click on the 'Email' Text box but do not send keys inside");
        clickEmailTextBox();

        System.out.println("Fill 'Password' Field");
        fillPasswordField(password);

        System.out.println("Fill 'Repeat Password' Field");
        fillRepeatPasswordField(password);

        System.out.println("Click on the 'Security Question' element to open drop down list");
        openSeqQuestionDropDownList();

        System.out.println("Choose option from SecQuestion drop down list");
        chooseOptionFromSecQuestionDropDownList();

        System.out.println("Fill 'Answer' field with text");
        fillSecurityQuestionTextBox(securityQuestionAnswer);

    }

    public Boolean isErrorWhenEmailFieldBlankDisplayed() {
        System.out.println("Is 'Please provide an email address' error displayed?");
        return waitUntilDisplayed((emptyEmailFieldErrorElement), 4).isDisplayed();
    }

    public Boolean isErrorAboutInvalidEmailDisplayed() {
        System.out.println("Is 'Email address is not valid.' error is displayed?");
        return driver.findElement(invalidEmailErrorElement).isDisplayed();
    }

    public Boolean isErrorAboutAlreadyExistsEmailDisplayed() {
        System.out.println("Is 'Email must be unique' error displayed?");
        return waitUntilDisplayed(alreadyExistsEmailErrorElement, 4).isDisplayed();
    }

    public Boolean isRegisterButtonInNotActiveState() {
        System.out.println("Is Register button in not active state?");
        return waitUntilDisplayed(registerButtonInDisabledStateElement, 3).isDisplayed();
    }


}
