package io.ctdev.lesson6.products;

import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.main.AllProductsPage;
import io.ctdev.lesson6.BaseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
    }

    @Test
    public void verifyInabilityAddSoldOutProductToCart() {
        allProductsPage.scrollToTheBottomOfThePage();
        allProductsPage.goToTheNextPage();
        allProductsPage.clickAddSoldOutProduct();
        Assert.assertTrue(allProductsPage.isOutOfStockErrorDisplayed());
    }

}
