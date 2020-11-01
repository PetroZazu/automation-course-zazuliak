package io.ctdev.lesson6.products;

import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.main.AllProductsPage;
import io.ctdev.lesson6.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

@Epic("Products")
@Story("Sold Out Products")

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

    @Test ()
    @Description("Check that Sold Out product can not be added to the cart")
    public void verifyInabilityAddSoldOutProductToCart() {
        System.out.println();
        System.out.println("verifyInabilityAddSoldOutProductToCart test");
        allProductsPage.scrollToTheBottomOfThePage();
        allProductsPage.goToTheNextPage();
        waitForPageToLoad(driver);
        allProductsPage.clickAddSoldOutProduct();
        Assert.assertTrue(allProductsPage.isOutOfStockErrorDisplayed());

    }

}
