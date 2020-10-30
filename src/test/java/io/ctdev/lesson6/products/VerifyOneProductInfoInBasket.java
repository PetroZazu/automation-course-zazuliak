package io.ctdev.lesson6.products;

import io.ctdev.framework.pages.bucket.BasketPage;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.main.AllProductsPage;
import io.ctdev.lesson6.BaseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.ctdev.framework.model.ProductItemsDescription.getItemDescriptionByPositionNumber;

public class VerifyOneProductInfoInBasket extends BaseTest {
    private SoftAssert softAssert = new SoftAssert();
    private AllProductsPage allProductsPage;
    private LoginPage loginPage;
    private BasketPage basketPage;
    private int productPositionNumber;
    private String productName;
    private String productImgLink;
    private String productPrice;
    private String productDescription;

    @BeforeClass
    public void setUP() {
        basketPage = new BasketPage(driver);
        loginPage = new LoginPage(driver);
        allProductsPage = new AllProductsPage(driver);
        productPositionNumber = allProductsPage.selectRandomProductNumber(12);
        loginPage.openPage();
        loginPage.closeWelcomeAndCookiesPopUps();
        loginPage.performLoginToTheAccount();
        allProductsPage.openPage();

        //waitForPageToLoad(driver);
    }

    @Test
    public void testOneProductInfoWhenInCart() {
        //productDescription = getItemDescriptionByPositionNumber(productPositionNumber);
        productName = allProductsPage.getProductName(productPositionNumber);
        productImgLink = allProductsPage.getProductImgSrc(productPositionNumber);
        productPrice = allProductsPage.getProductPriceOnPage(productPositionNumber);
        allProductsPage.scrollToTheSelectedProduct(productPositionNumber);
        allProductsPage.clickAddProductToTheCart(productPositionNumber, 1);
        allProductsPage.goToTheShoppingCart();
        softAssert.assertEquals(basketPage.getProductName(), productName, "Name of the product on page and name of the product in basket, doesn't equals");
        softAssert.assertEquals(basketPage.getProductImgSrc(), productImgLink, "Product img SRC on page and Product img SRC in basket doesn't equals");
        softAssert.assertEquals(basketPage.getProductPrice(), productPrice, "Product Price on page and Product Price in basket doesn't equals");
        softAssert.assertEquals(basketPage.getProductCount(), allProductsPage.getHowManyProductsAdded(), "Count of added products doesn't appropriate");
        softAssert.assertAll();
    }

}
