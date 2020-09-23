package io.ctdev.lesson5.juiceshophw2;

import io.ctdev.lesson4.juiceshophw.BaseTestJuiceShop;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class verifyProductInfo extends BaseTestJuiceShop {
    int productPositionNumber = 12;

    private String productNameOnPage;
    private String productImgLinkOnPage;
    private String productPriceOnPage;


    @AfterMethod
    public void beforeEachTest() {
        System.out.println("Before Each Test, page refreshed");
        driver.navigate().refresh();
    }

    @Test
    public void verifyProductNameOnPageVsProductNameInPopUp() {
        productNameOnPage = getProductName();
        clickOnTheProduct();
        printActualAndExpectedProductName();
        Assert.assertTrue(getProductNameInThePopUp().equals(productNameOnPage));
    }

    @Test
    public void verifyProductImgLinkOnPageVsProductImgLinkInPopUp() {
        productImgLinkOnPage = getProductImgSrc();
        clickOnTheProduct();
        printActualAndExpectedProductImgSrc();
        Assert.assertTrue(getItemImgLinkInThePopUp().equals(productImgLinkOnPage));
    }

    @Test
    public void verifyProductPriceOnThePageVsProductPriceInThePopUp() {
        productPriceOnPage = getProductPriceOnPage();
        clickOnTheProduct();
        printActualAndExpectedProductPrice();
        Assert.assertTrue(getProductPriceInThePopUp().equals(productPriceOnPage));
    }

    @Test
    public void verifyProductDescription() {
        fillArrayWithItemsDescription();
        clickOnTheProduct();
        printActualAndExpectedProductDescription();
        Assert.assertTrue(getItemDescription().equals(itemsDescriptionPage1.get(productPositionNumber)));

    }


    public String getProductName() {
        return waitUntilDisplayed(By.xpath("(//figure)[" + productPositionNumber + "]//div[@class='item-name']"), 5).getText();
    }

    public String getProductImgSrc() {
        return waitUntilDisplayed(By.xpath("(//figure)[" + productPositionNumber + "]//img"), 3).getAttribute("src");
    }

    public String getProductPriceOnPage() {
        return waitUntilDisplayed(By.xpath("(//figure)[" + productPositionNumber + "]//div[@class='item-price']/span"), 3).getText();
    }

    public String getItemDescription() {
        return waitUntilDisplayed(By.xpath("//mat-dialog-container//h1/following::div[1]"),4).getText();
    }

    public void clickOnTheProduct() {
        waitUntilDisplayed(By.xpath("(//figure)[" + productPositionNumber + "]//div[@class='item-name']"), 6).click();
    }

    public String getProductNameInThePopUp() {
        return waitUntilDisplayed((By.xpath("//mat-dialog-content//h1")), 4).getText();
    }

    public String getItemImgLinkInThePopUp() {
        return waitUntilDisplayed(By.xpath("//mat-dialog-content//img"), 4).getAttribute("src");
    }

    public String getProductPriceInThePopUp() {
        return waitUntilDisplayed((By.xpath("//mat-dialog-content//p")), 4).getText();
    }

    public void printActualAndExpectedProductName() {
        System.out.println("actual Product Name is: " + "'" + getProductNameInThePopUp() + "'");
        System.out.println("expected Product Name is: " + "'" + productNameOnPage + "'");
    }

    public void printActualAndExpectedProductImgSrc() {
        System.out.println("actual Product Image Src is: " + "'" + getItemImgLinkInThePopUp() + "'");
        System.out.println("expected Product Image Src is: " + "'" + productImgLinkOnPage + "'");
    }

    public void printActualAndExpectedProductPrice() {
        System.out.println("actual Product Price is: " + "'" + getProductPriceInThePopUp() + "'");
        System.out.println("expected Product Image Src is: " + "'" + productPriceOnPage + "'");
    }

    public void printActualAndExpectedProductDescription() {
        System.out.println("actual Product description is: " + "'" + getItemDescription() + "'");
        System.out.println("expected Product description is: " + "'" + itemsDescriptionPage1.get(productPositionNumber) + "'");
    }

    public void fillArrayWithItemsDescription (){
        itemsDescriptionPage1.add(0,"null");
        itemsDescriptionPage1.add(1, "The all-time classic.");
        itemsDescriptionPage1.add(2, "Finest pressings of apples. Allergy disclaimer: Might contain traces of worms. Can be sent back to us for recycling.");
        itemsDescriptionPage1.add(3, "Monkeys love it the most.");
        itemsDescriptionPage1.add(4, "As the old German saying goes: \"Carrots are good for the eyes. Or has anyone ever seen a rabbit with glasses?\"");
        itemsDescriptionPage1.add(5, "Now with even more exotic flavour.");
        itemsDescriptionPage1.add(6, "Fruits go in. Juice comes out. Pomace you can send back to us for recycling purposes.");
        itemsDescriptionPage1.add(7, "Looks poisonous but is actually very good for your health! Made from green cabbage, spinach, kiwi and grass.");
        itemsDescriptionPage1.add(8, "Common rarity \"Juice Shop\" card for the Adversary Trading Cards CCG.");
        itemsDescriptionPage1.add(9, "Super rare \"Juice Shop\" card with holographic foil-coating for the Adversary Trading Cards CCG.");
        itemsDescriptionPage1.add(10, "Unique masterpiece painted with different kinds of juice on 90g/m² lined paper.");
        itemsDescriptionPage1.add(11, "Sour but full of vitamins.");
        itemsDescriptionPage1.add(12, "The wheels of this bicycle are made from real water melons. You might not want to ride it up/down the curb too hard.");

    }
}
