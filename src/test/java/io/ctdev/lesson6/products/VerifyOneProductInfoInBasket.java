package io.ctdev.lesson6.products;

import io.ctdev.framework.pages.bucket.BasketPage;
import io.ctdev.framework.pages.login.LoginPage;
import io.ctdev.framework.pages.main.AllProductsPage;
import io.ctdev.lesson6.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Epic("Products")
@Story("Products basket")

public class VerifyOneProductInfoInBasket extends BaseTest {
    private SoftAssert softAssert = new SoftAssert();
    private AllProductsPage allProductsPage;
    private LoginPage loginPage;
    private BasketPage basketPage;
    private int productPositionNumber;
    private String productName;
    private String productImgLink;
    private String productPrice;

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
    }

    @Test
    @Description("Check that product Name, Img, Price, and count of added product is the same as was on the main page")
    public void testOneProductInfoWhenInCart() {
        System.out.println();
        System.out.println("testOneProductInfoWhenInCart Test");
        productName = allProductsPage.getProductName(productPositionNumber);
        productImgLink = allProductsPage.getProductImgSrc(productPositionNumber);
        productPrice = allProductsPage.getProductPriceOnPage(productPositionNumber);

        allProductsPage.clickAddProductToTheCart(productPositionNumber, 1);
        allProductsPage.goToTheShoppingCart();
        softAssert.assertEquals(basketPage.getProductName(), productName, "Name of the product on page and name of the product in basket, doesn't equals");
        softAssert.assertEquals(basketPage.getProductImgSrc(), productImgLink, "Product img SRC on page and Product img SRC in basket doesn't equals");
        softAssert.assertEquals(basketPage.getProductPrice(), productPrice, "Product Price on page and Product Price in basket doesn't equals");
        softAssert.assertEquals(basketPage.getProductCount(), allProductsPage.getHowManyProductsAdded(), "Count of added products doesn't appropriate");
        softAssert.assertAll();
    }

}
