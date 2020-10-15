package io.ctdev.lesson6.products;


import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.main.AllProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class VerifySoldOutProduct {
    private WebDriver driver = getDriver();
    private AllProductsPage allProductsPage = new AllProductsPage(driver);
    private LoginPage loginPage = new LoginPage(driver);

    @BeforeClass
    public void setUp() {
        loginPage.openPage();
        loginPage.closeWelcomeAndCookiesPopUps();
        loginPage.performLoginToTheAccount();
        allProductsPage.openPage();
    }

    @Test
    public void verifyInabilityAddSoldOutProductToCart() {
        allProductsPage.scrollToTheBottomOfThePage();
        allProductsPage.goToTheNextPage();
        allProductsPage.clickAddSoldOutProduct();
        Assert.assertTrue(allProductsPage.isOutOfStockErrorDisplayed());
    }

    @AfterClass
    public void afterClass() {
        closeDriver();
    }


}
