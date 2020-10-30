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
                        4).getText();
        System.out.println("Product name in basket is: " + productNameInBasket);
        return productNameInBasket;
        //return waitUntilDisplayed(productNameElement, 5).getText();
    }

    public int getProductCount() {
        return Integer.parseInt(waitUntilDisplayed(countOfProductElement, 5).getText());
    }

    public String getProductPrice() {
        return waitUntilDisplayed(productPriceElement, 5).getText();
    }

    public String getProductImgSrc() {
        return waitUntilDisplayed(productImgSrcElement, 5).getAttribute("src");
    }


}
