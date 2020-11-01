package io.ctdev.framework.pages.bucket;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends AbstractPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By productNameElement = By.xpath("//mat-row[1]//mat-cell[2]");
    private By countOfProductElement = By.xpath("//mat-row[1]//mat-cell[3]/span");
    private By productPriceElement = By.xpath("//mat-row[1]//mat-cell[4]");
    private By productImgSrcElement = By.xpath("//mat-row[1]//mat-cell[1]/img");

    public BasketPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    @Override
    public void openPage() {
        System.out.println("Open Juice Shop Bucket Page");
        driver.get(TestConfig.cfg.juiceShopBucketPage());
    }

    @Step("Collect product name in the basket")
    public String getProductName() {
        System.out.println("Collect product name in basket");
        waitUntilDisplayed(productNameElement, 5);
        String productNameInBasket = waitUntilAttributeLoaded
                (driver.findElement(productNameElement),
                        "innerText",
                        12).getText();
        System.out.println("Product name in basket is: " + productNameInBasket);
        return productNameInBasket;

    }

    @Step("Collect product counts in the basket")
    public int getProductCount() {
        System.out.println("Collect product count in basket");
        waitUntilDisplayed(countOfProductElement, 5);
        String productCountInBasket = waitUntilAttributeLoaded
                (driver.findElement(countOfProductElement),
                        "outerText",
                        4).getAttribute("outerText");
        //productCountInBasket = "1";
        System.out.println("Product counts in basket is: " + productCountInBasket);
        return Integer.parseInt(productCountInBasket);
    }

    @Step("Collect product price in the basket")
    public String getProductPrice() {
        System.out.println("Collect product price in basket");
        waitUntilDisplayed(productPriceElement, 5);
        String productPriceInBasket = waitUntilAttributeLoaded
                (driver.findElement(productPriceElement),
                        "innerText",
                        12).getAttribute("innerText");
        System.out.println("Product price in basket is: " + productPriceInBasket);
        return productPriceInBasket;
    }

    @Step("Collect product img. SRC in the basket")
    public String getProductImgSrc() {
        System.out.println("Collect product img. src in basket");
        waitUntilDisplayed(productImgSrcElement, 5);
        String productImgSrcInBasket = waitUntilAttributeLoaded
                (driver.findElement(productImgSrcElement),
                        "src",
                        12).getAttribute("src");
        System.out.println("Product img Src in basket is: " + productImgSrcInBasket);
        return productImgSrcInBasket;
    }


}
