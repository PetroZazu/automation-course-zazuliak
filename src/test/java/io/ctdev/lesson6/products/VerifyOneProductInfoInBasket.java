package io.ctdev.lesson6.products;

import io.ctdev.framework.pages.bucket.BasketPage;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.main.AllProductsPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.ctdev.framework.driver.WebDriverSingleton.closeDriver;
import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;
import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

public class VerifyOneProductInfoInBasket {
    private WebDriver driver = getDriver();
    private SoftAssert softAssert = new SoftAssert();
    private AllProductsPage allProductsPage = new AllProductsPage(driver);
    private LoginPage loginPage = new LoginPage(driver);
    private BasketPage basketPage = new BasketPage(driver);
    private int productPositionNumber;
    private String productName;
    private String productImgLink;
    private String productPrice;

    @BeforeClass
    public void setUP() {
        productPositionNumber = allProductsPage.selectRandomProductNumber(12);
        loginPage.openPage();
        loginPage.closeWelcomeAndCookiesPopUps();
        loginPage.performLoginToTheAccount();
        allProductsPage.openPage();
        //waitForPageToLoad(driver);
    }

    @Test
    public void testOneProductInfoWhenInCart() {

        productName = allProductsPage.getProductName(productPositionNumber);
        productImgLink = allProductsPage.getProductImgSrc(productPositionNumber);
        productPrice = allProductsPage.getProductPriceOnPage(productPositionNumber);
        //allProductsPage.scrollToTheSelectedProduct(productPositionNumber);
        allProductsPage.clickAddProductToTheCart(productPositionNumber, 3);
        allProductsPage.goToTheShoppingCart();
        softAssert.assertEquals(basketPage.getProductName(), productName, "Name of the product on page and name of the product in basket, doesn't equals");
        softAssert.assertEquals(basketPage.getProductImgSrc(), productImgLink, "Product img SRC on page and Product img SRC in basket doesn't equals");
        softAssert.assertEquals(basketPage.getProductPrice(), productPrice, "Product Price on page and Product Price in basket doesn't equals");
        softAssert.assertEquals(basketPage.getProductCount(), allProductsPage.getHowManyProductsAdded(), "Count of added products doesn't appropriate");
        softAssert.assertAll();
    }


    @AfterClass
    public void afterClass() {
        closeDriver();
    }
}
