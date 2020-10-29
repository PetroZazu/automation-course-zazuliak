package io.ctdev.framework.pages.main;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.model.ProductItemsDescription;
import io.ctdev.framework.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class AllProductsPage extends AbstractPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private String productElementOnPage = "(//figure)[%s]//div[@class='item-name']";
    private String productNameTextElement = "(//figure)[%s]//div[@class='item-name']";
    private String productImgSrcElement = "(//figure)[%s]//img";
    private String productPriceTextElement = "(//figure)[%s]//div[@class='item-price']/span";
    private String productAddToCartButton = "(//mat-card)[%s]//button";
    private By productNameInThePopUp = By.xpath("//mat-dialog-content//h1");
    private By productImgLinkInThePopUp = By.xpath("//mat-dialog-content//img");
    private By productPriceInThePopUp = By.xpath("//mat-dialog-content//p");
    private By itemDescriptionTextElementInPop = By.xpath("//mat-dialog-container//h1/following::div[1]");
    private By goToTheNextPageButton = By.xpath("//button[contains(@aria-label, 'Next page')]");
    private By shoppingCartButtonElement = By.xpath("//mat-icon[contains(text(), 'shopping_cart')]");
    private By soldOutProductAddButton = By.xpath("(//mat-card//span[contains(text(), 'Sold Out')]/../..//span[contains(text(), 'Add to Basket')])[1]");
    private By outOfStockErrorMessage = By.xpath("//span[contains(text(), 'We are out of stock')]");
    private By youCanOrderUpToOneItemErrorMessage = By.xpath("//span[contains(text(), 'You can order only up to 1 items')]");
    private By productPlacedConfirmPopUpElement = By.xpath("//span[contains(text(), 'Added') or contains(text(), 'Placed')]");
    private By pageBottomElement = By.xpath("//div[@class='mat-paginator-container']");

    private int countOfAddedProduct;

    private ProductItemsDescription productItemsDescription = new ProductItemsDescription();

    public AllProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    public void openPage() {
        System.out.println("Open Main Web Page");
        driver.get(TestConfig.cfg.juiceShopMainPage());
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    public void clickOnTheProduct(int productPositionNumber) {
        waitUntilDisplayed(By.xpath(String.format(productElementOnPage, productPositionNumber)), 3).click();
    }

    public void clickAddProductToTheCart(int productPosition, int homManyItemsToAdd) {
        System.out.println("this product will be added to the cart " + homManyItemsToAdd + " times");
        for (int i = 1; i <= homManyItemsToAdd; i++) {
            waitUntilDisplayed(By.xpath(String.format(productAddToCartButton, productPosition)), 6).click();
            if (isOutOfStockErrorDisplayed() || isYouCanOrderUpToOneItemErrorDisplayed()) {
                countOfAddedProduct = i - 1;
                System.out.println("Not all items of the current product was available or can not be added ");
                System.out.println(countOfAddedProduct + " item/s of current product was added to the cart instead of " + homManyItemsToAdd);
                break;
            }
            waitUntilDisplayed(productPlacedConfirmPopUpElement, 2);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(productPlacedConfirmPopUpElement));
            countOfAddedProduct = i;
        }
        System.out.println("To the cart was added " + countOfAddedProduct + " item/s of selected product");
    }

    public void goToTheShoppingCart() {
        waitUntilDisplayed(shoppingCartButtonElement, 2).click();
    }

    public void goToTheNextPage() {
        System.out.println("Click 'Next page' button");
        waitForElementToBeClickable(goToTheNextPageButton, 5).click();
    }

    public void clickAddSoldOutProduct() {
        System.out.println("Try to add sold out product to the cart");
        waitUntilDisplayed(soldOutProductAddButton, 3).click();
    }

    public int getHowManyProductsAdded() {
        return countOfAddedProduct;
    }

    public String getProductName(int productPositionNumber) {
        System.out.println("Collect product Name from page");
        printProductName(productPositionNumber);
        return waitUntilDisplayed(By.xpath(String.format(productNameTextElement, productPositionNumber)), 3).getText();

    }

    public String getProductImgSrc(int productPositionNumber) {
        System.out.println("Collect product IMG src from page");
        return waitUntilDisplayed(By.xpath(String.format(productImgSrcElement, productPositionNumber)), 3).getAttribute("src");
    }

    public String getProductPriceOnPage(int productPositionNumber) {
        System.out.println("Collect product price from page");
        return waitUntilDisplayed(By.xpath(String.format(productPriceTextElement, productPositionNumber)), 3).getText();
    }

    public String getItemDescriptionTextInPopUp() {
        return waitUntilDisplayed(itemDescriptionTextElementInPop, 4).getText();
    }

    public String getProductNameInThePopUp() {
        return waitUntilDisplayed(productNameInThePopUp, 4).getText();
    }

    public String getItemImgLinkInThePopUp() {
        return waitUntilDisplayed(productImgLinkInThePopUp, 4).getAttribute("src");
    }

    public String getProductPriceInThePopUp() {
        return waitUntilDisplayed(productPriceInThePopUp, 4).getText();
    }

    public Boolean isOutOfStockErrorDisplayed() {
        try {
            return waitUntilDisplayed(outOfStockErrorMessage, 7).isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public Boolean isYouCanOrderUpToOneItemErrorDisplayed() {
        try {
            return waitUntilDisplayed(youCanOrderUpToOneItemErrorMessage, 1).isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }

    }

    public void scrollToTheBottomOfThePage() {
        scrollToTheElement(pageBottomElement);
    }

    public void scrollToTheSelectedProduct(int productPositionNumber) {
        scrollToTheElement(By.xpath(String.format(productAddToCartButton, productPositionNumber)));
        waitForElementToBeClickable(By.xpath(String.format(productAddToCartButton, productPositionNumber)), 5);
    }

    public void printProductName(int productPositionNumber) {
        System.out.println("Collected product name is: " + waitUntilDisplayed(By.xpath(String.format(productNameTextElement, productPositionNumber)), 3).getText());
    }

    public void printActualAndExpectedProductName(String productNameOnPage) {
        System.out.println("actual Product Name is: " + "'" + this.getProductNameInThePopUp() + "'");
        System.out.println("expected Product Name is: " + "'" + productNameOnPage + "'");
    }

    public void printActualAndExpectedProductImgSrc(String productImgLinkOnPage) {
        System.out.println("actual Product Image Src is: " + "'" + this.getItemImgLinkInThePopUp() + "'");
        System.out.println("expected Product Image Src is: " + "'" + productImgLinkOnPage + "'");
    }

    public void printActualAndExpectedProductPrice(String productPriceOnPage) {
        System.out.println("actual Product Price is: " + "'" + this.getProductPriceInThePopUp() + "'");
        System.out.println("expected Product Price is: " + "'" + productPriceOnPage + "'");
    }

    public void printActualAndExpectedProductDescription(int productPositionNumber) {
        System.out.println("actual Product description is: " + "'" + this.getItemDescriptionTextInPopUp() + "'");
        System.out.println("expected Product description is: " + "'" + productItemsDescription.getItemDescriptionByPositionNumber(productPositionNumber) + "'");
    }

    public void printActualAndExpectedProductDescription(String productName) {
        System.out.println("actual Product description is: " + "'" + this.getItemDescriptionTextInPopUp() + "'");
        System.out.println("expected Product description is: " + "'" + productItemsDescription.getItemDescriptionByName(productName) + "'");
    }


    public int selectRandomProductNumber(int numberOfProductsOnPage) {
        numberOfProductsOnPage++;
        int productPositionNumber = new Random().nextInt(numberOfProductsOnPage);
        if (productPositionNumber == 0) {
            productPositionNumber++;
        }
        System.out.println("Test product position number is: " + productPositionNumber);
        return productPositionNumber;
    }
}
