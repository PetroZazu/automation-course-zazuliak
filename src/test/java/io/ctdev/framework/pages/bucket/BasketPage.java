package io.ctdev.framework.pages.bucket;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends AbstractPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By productNameElement = By.xpath("//mat-row[1]//mat-cell[2]");
    private By countOfProductElement = By.xpath("//mat-row[1]//mat-cell[3]");
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

    public String getProductName() {
        System.out.println("Collect product name in basket");
        String productNameInBasket = waitUntilAttributeLoaded
                (driver.findElement(productNameElement),
                        "innerText",
                        12).getText();
        System.out.println("Product name in basket is: " + productNameInBasket);
        return productNameInBasket;
        //return waitUntilDisplayed(productNameElement, 5).getText();
    }

    public int getProductCount() {
        System.out.println("Collect product count in basket");
        String productCountInBasket = waitUntilAttributeLoaded
                (driver.findElement(countOfProductElement),
                        "innerText",
                        12).getText();
        System.out.println("Product counts in basket is: " + productCountInBasket);
        return Integer.parseInt(productCountInBasket);
    }

    public String getProductPrice() {
        System.out.println("Collect product price in basket");
        String productPriceInBasket = waitUntilAttributeLoaded
                (driver.findElement(productPriceElement),
                        "innerText",
                        12).getText();
        System.out.println("Product price in basket is: " + productPriceInBasket);
        return productPriceInBasket;
    }

    public String getProductImgSrc() {
        System.out.println("Collect product img. src in basket");
        String productImgSrcInBasket = waitUntilAttributeLoaded
                (driver.findElement(productImgSrcElement),
                        "currentSrc",
                        12).getAttribute("src");
        System.out.println("Product img Src in basket is: " + productImgSrcInBasket);
        return productImgSrcInBasket;
    }


}
