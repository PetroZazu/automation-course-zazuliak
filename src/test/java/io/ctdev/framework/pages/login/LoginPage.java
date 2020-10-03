package io.ctdev.framework.pages.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
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
    public void openPage() {
        System.out.println("Open Login Web Page");
        driver.get(TestConfig.cfg.juiceShopLoginPage());
        System.out.println("Using cookies adjustment, banner and cookies consent pop-ups will be closed");
        driver.manage().addCookie(new Cookie("cookieconsent_status", "dismiss"));
        driver.manage().addCookie(new Cookie("welcomebanner_status", "dismiss"));
        driver.navigate().refresh();
    }

    public void clickOnAccountButton() {
        System.out.println("Click on the 'Account' button");
        driver.findElement(navBarAccountElement).click();
    }

    public void clickOnLoginButton() {
        System.out.println("Click on the 'LogIn' button");
        driver.findElement(navBarLoginButtonElement).click();
    }


    public void clickOnSubmitLoginButton() {
        System.out.println("Click on the 'Submit Login' Button");
        waitUntilDisplayed(submitLoginButtonElement, 4).click();
    }

    public void fillEmailTextBox(String email) {
        System.out.println("Fill 'Email' field with text: " + email);
        waitUntilDisplayed(emailTextBoxElement, 5).sendKeys(email);
    }

    public void fillPasswordTextBox(String password) {
        System.out.println("Fill 'Password' field with text: " + password);
        waitUntilDisplayed(passwordTextBox,5).sendKeys(password);
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

    public void clickInAndClickOutPasswordTextBox() {
        driver.findElement(passwordTextBox).click();
        driver.findElement(loginPageTitleTextElement).click();
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
