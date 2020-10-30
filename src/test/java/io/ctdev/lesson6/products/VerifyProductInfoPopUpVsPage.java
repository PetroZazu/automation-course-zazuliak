package io.ctdev.lesson6.products;

import io.ctdev.framework.model.ProductItemsDescription;
import io.ctdev.framework.pages.main.AllProductsPage;
import io.ctdev.lesson6.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.pages.AbstractPage.waitForPageToLoad;

public class VerifyProductInfoPopUpVsPage extends BaseTest {
    private AllProductsPage allProductsPage;
    private ProductItemsDescription productItemsDescription;

    private int productPositionNumber;

    private String productNameOnPage;
    private String productImgLinkOnPage;
    private String productPriceOnPage;

    @BeforeClass
    public void setUp() {
        allProductsPage = new AllProductsPage(driver);
        productItemsDescription = new ProductItemsDescription();
        productPositionNumber = allProductsPage.selectRandomProductNumber(12);

        allProductsPage.openPage();
        allProductsPage.closeWelcomeAndCookiesPopUps();

        productNameOnPage = allProductsPage.getProductName(productPositionNumber);
        productImgLinkOnPage = allProductsPage.getProductImgSrc(productPositionNumber);
        productPriceOnPage = allProductsPage.getProductPriceOnPage(productPositionNumber);

    }

    @AfterMethod
    public void afterMethod() {
        driver.navigate().refresh();
        waitForPageToLoad(driver);
        driver.navigate().refresh();
        waitForPageToLoad(driver);
    }

    @Test
    public void verifyProductNameOnPageVsProductNameInPopUp() {
        allProductsPage.clickOnTheProduct(productPositionNumber);
        allProductsPage.printActualAndExpectedProductName(productNameOnPage);
        Assert.assertTrue(allProductsPage.getProductNameInThePopUp().equals(productNameOnPage));
    }

    @Test
    public void verifyProductImgLinkOnPageVsProductImgLinkInPopUp() {
        allProductsPage.clickOnTheProduct(productPositionNumber);
        allProductsPage.printActualAndExpectedProductImgSrc(productImgLinkOnPage);
        Assert.assertTrue(allProductsPage.getItemImgLinkInThePopUp().equals(productImgLinkOnPage));
    }

    @Test
    public void verifyProductPriceOnThePageVsProductPriceInThePopUp() {
        allProductsPage.clickOnTheProduct(productPositionNumber);
        allProductsPage.printActualAndExpectedProductPrice(productPriceOnPage);
        Assert.assertTrue(allProductsPage.getProductPriceInThePopUp().equals(productPriceOnPage));
    }

    @Test
    public void verifyProductDescription() {
        allProductsPage.clickOnTheProduct(productPositionNumber);
        allProductsPage.printActualAndExpectedProductDescription(productNameOnPage);
        Assert.assertTrue(allProductsPage.getProductDescriptionTextInPopUp().equals(productItemsDescription.getItemDescriptionByName(productNameOnPage)));

    }

}
