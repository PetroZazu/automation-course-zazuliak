package io.ctdev.framework.pages.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By navBarAccountElement = By.cssSelector("#navbarAccount");
    private By navBarLoginButtonElement = By.cssSelector("#navbarLoginButton");
    private By submitLoginButtonElement = By.id("loginButton");
    private By emailTextBoxElement = By.id("email");
    private By passwordTextBox = By.id("password");
    private By currentLoggedInUserEmailElement = By.cssSelector("[aria-label='Go to user profile'] span");
    private By loginPageTitleTextElement = By.xpath("//h1[text()='Login']");
    private By invalidLoginOrPasswordErrorElement = By.xpath("//div[contains(text(), 'Invalid email or password')]");
    private By pleaseProvidePasswordErrorElement = By.xpath("//mat-error[contains(text(), 'Please provide a password')]");
    private By inactiveLoginButtonElement = By.xpath("//button[@id='loginButton' and @disabled='true']");


    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }


    @Override
    @Step("Open Login page")
    public void openPage() {
        System.out.println("Open Login Web Page");
        driver.get(TestConfig.cfg.juiceShopLoginPage());
    }

    @Step("Fill Email, Password fields and click login button")
    public void performLoginToTheAccount() {
        fillEmailTextBox("pzzzzazuliak@yopmail.com");
        fillPasswordTextBox("@z$rt&12!!azazaza");
        clickOnSubmitLoginButton();
        clickOnAccountButton();
    }

    @Step("Click on the 'Account' button")
    public void clickOnAccountButton() {
        System.out.println("Click on the 'Account' button");
        driver.findElement(navBarAccountElement).click();
    }

    @Step
    public void clickOnLoginButton() {
        System.out.println("Click on the 'LogIn' button");
        driver.findElement(navBarLoginButtonElement).click();
    }

    @Step("Click on the 'Submit Login' button")
    public void clickOnSubmitLoginButton() {
        System.out.println("Click on the 'Submit Login' Button");
        waitUntilDisplayed(submitLoginButtonElement, 4).click();
    }

    @Step("Fill 'Email' text box")
    public void fillEmailTextBox(String email) {
        System.out.println("Fill 'Email' field with text: " + email);
        waitUntilDisplayed(emailTextBoxElement, 5).sendKeys(email);
    }

    @Step("Fill 'Password' text box")
    public void fillPasswordTextBox(String password) {
        System.out.println("Fill 'Password' field with text: " + password);
        waitUntilDisplayed(passwordTextBox, 5).sendKeys(password);
    }


    public String getCurrentLoggedInUserEmail() {
        WebElement userNameElement = wait.until(ExpectedConditions.presenceOfElementLocated(currentLoggedInUserEmailElement));
        String actualUserName = userNameElement.getAttribute("innerText").trim();

        for (int i = 0; actualUserName.equals(""); i++) {
            System.out.println("iter");
            actualUserName = userNameElement.getAttribute("innerText").trim();

            if (i == 55)
                break;
        }

        return actualUserName;
    }

    @Step
    public void clickInAndClickOutPasswordTextBox() {
        driver.findElement(passwordTextBox).click();
        driver.findElement(loginPageTitleTextElement).click();
    }

    @Step
    public void clearEmailAndPasswordFields() {
        driver.findElement(passwordTextBox).clear();
        driver.findElement(emailTextBoxElement).clear();
    }

    public boolean isInvalidLoginOrPasswordErrorMessageDisplayed() {
        return waitUntilDisplayed(invalidLoginOrPasswordErrorElement, 4).isDisplayed();
    }

    public boolean isEmptyPasswordErrorDisplayed() {
        return waitUntilDisplayed(pleaseProvidePasswordErrorElement, 4).isDisplayed();
    }

    public boolean isSubmitButtonInactive() {
        return waitUntilDisplayed(inactiveLoginButtonElement, 4).isDisplayed();
    }

}
