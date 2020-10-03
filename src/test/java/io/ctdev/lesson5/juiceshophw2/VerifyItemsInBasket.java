package io.ctdev.lesson5.juiceshophw2;

import io.ctdev.lesson4.juiceshophw.BaseTestJuiceShop;
import io.ctdev.lesson5.juiceshophw2.preconditions.AccountActions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class VerifyItemsInBasket extends BaseTestJuiceShop {
    private int productPosition = 3;
    private String productName;
    private String productPrice;
    private String productImg;
    private int countOfProducts;


    @BeforeClass
    public void beforeClass2() {
        //log in to app
        AccountActions.logInToTheAccount(driver);
        //Collect product info to have what to verify in feature
        setAllDataAboutProducts();
        //Add product to the cart
        addProductToTheCart(2);
        //Click shoping cart
        clickShopingCart();
    }

    @Test
    public void productNameInCartVerefication() {
        verifyProductName();
    }

    @Test
    public void verifyCountOfOneProductInTheCart() {
        verifyProductCount();
    }

    @Test
    public void verifyOneProductPriceInCart() {
        verifyTheProductPrice();
    }

    @Test
    public void verifyProductImgInCart() {
        verifyProductImg();
    }

    public void setAllDataAboutProducts() {
        productName = waitUntilDisplayed(By.xpath("(//figure)[" + productPosition + "]//div[@class='item-name']"), 5).getText();
        productPrice = waitUntilDisplayed(By.xpath("(//figure)[" + productPosition + "]//div[@class='item-price']/span"), 3).getText();
        productImg = waitUntilDisplayed(By.xpath("(//figure)[" + productPosition + "]//img"), 3).getAttribute("src");

    }

    public void addProductToTheCart(int homManyItemsToAdd) {
        for (int i = 1; i <= homManyItemsToAdd; i++) {
            waitUntilDisplayed(By.xpath("(//mat-card)[" + productPosition + "]//button"), 3).click();
            waitUntilDisplayed(By.xpath("//span[contains(text(), 'Added') or contains(text(), 'Placed')]"), 3);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(), 'Added') or contains(text(), 'Placed')]")));
            countOfProducts++;
        }
    }

    public void clickShopingCart() {
        waitUntilDisplayed(By.xpath("//mat-icon[contains(text(), 'shopping_cart')]"), 2).click();
    }

    public void verifyProductName() {
        String productNameInCart = waitUntilDisplayed(By.xpath("//mat-row[1]//mat-cell[2]"), 5).getText();
        System.out.println("Actual Product text is: " + productNameInCart);
        System.out.println("Expected Product text is: " + productName);
        Assert.assertEquals(productNameInCart, productName);
    }

    public void verifyProductCount() {
        String cartProductCountInString = waitUntilDisplayed(By.xpath("//mat-row[1]//mat-cell[3]"), 5).getText();
        int actualProductCountInCart = Integer.parseInt(cartProductCountInString);
        Assert.assertEquals(actualProductCountInCart, countOfProducts);
    }

    public void verifyTheProductPrice() {
        String cartProductPrice = waitUntilDisplayed(By.xpath("//mat-row[1]//mat-cell[4]"), 5).getText();
        Assert.assertEquals(productPrice, cartProductPrice);
    }

    public void verifyProductImg() {
        String cartProductImg = waitUntilDisplayed(By.xpath("//mat-row[1]//mat-cell[1]/img"), 5).getAttribute("src");
        Assert.assertEquals(cartProductImg, productImg);
    }

    public void verifyProductPrice() {

    }


}
