package io.ctdev.lesson6.products;

import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.main.AllProductsPage;
import io.ctdev.lesson6.BaseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

public class VerifySoldOutProduct extends BaseTest {
    private AllProductsPage allProductsPage;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        loginPage = new LoginPage(driver);
        allProductsPage = new AllProductsPage(driver);
        loginPage.openPage();
        loginPage.closeWelcomeAndCookiesPopUps();
        loginPage.performLoginToTheAccount();
        allProductsPage.openPage();
        driver.navigate().refresh();
        waitForPageToLoad(driver);
    }

    @Test
    public void verifyInabilityAddSoldOutProductToCart() {
        allProductsPage.scrollToTheBottomOfThePage();
        allProductsPage.goToTheNextPage();
        waitForPageToLoad(driver);
        driver.navigate().refresh();
        allProductsPage.clickAddSoldOutProduct();
        Assert.assertTrue(allProductsPage.isOutOfStockErrorDisplayed());
    }

}
