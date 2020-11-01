package io.ctdev.framework.pages.main;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.model.ProductItemsDescription;
import io.ctdev.framework.pages.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private By itemDescriptionTextElementInPopUp = By.xpath("//mat-dialog-container//h1/following::div[1]");
    private By goToTheNextPageButton = By.xpath("//button[contains(@aria-label, 'Next page')]");
    private By shoppingCartButtonElement = By.xpath("//mat-icon[contains(text(), 'shopping_cart')]");
    private By soldOutProductAddButton = By.xpath("(//mat-card//span[contains(text(), 'Sold Out')]/../..//button)[1]");
    private By outOfStockErrorMessage = By.xpath("//span[contains(text(), 'We are out of stock')]");
    private By youCanOrderUpToOneItemErrorMessage = By.xpath("//span[contains(text(), 'You can order only up to')]");
    private By productPlacedConfirmPopUpElement = By.xpath("//span[contains(text(), 'Added') or contains(text(), 'Placed')]");
    private By pageBottomElement = By.xpath("//div[@class='mat-paginator-container']");
    private By xButtonInPopUpElement = By.xpath("//button/span[text()='X']");

    private int countOfAddedProduct;

    private ProductItemsDescription productItemsDescription = new ProductItemsDescription();

    public AllProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Go to the Juice Shop home Web Page")
    @Override
    public void openPage() {
        System.out.println("Open Main Web Page");
        driver.get(TestConfig.cfg.juiceShopMainPage());
        this.wait = new WebDriverWait(driver, TIME_OUT);
    }

    public void clickOnTheProduct(int productPositionNumber) {
        waitUntilDisplayed(By.xpath(String.format(productElementOnPage, productPositionNumber)), 3).click();
    }

    @Step("Add product to the cart one or more times")
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

            wait.until(ExpectedConditions.invisibilityOfElementLocated(productPlacedConfirmPopUpElement));
            countOfAddedProduct = i;
        }
        System.out.println("To the cart was added " + countOfAddedProduct + " item/s of selected product");
    }

    @Step ("Go to the shopping car")
    public void goToTheShoppingCart() {
        waitUntilDisplayed(shoppingCartButtonElement, 2).click();
    }

    public void goToTheNextPage() {
        System.out.println("Click 'Next page' button");
        waitForElementToBeClickable(goToTheNextPageButton, 12).click();
    }

    public void clickAddSoldOutProduct() {
        System.out.println("Try to add sold out product to the cart");
        waitUntilDisplayed(soldOutProductAddButton, 12).click();
    }

    public int getHowManyProductsAdded() {
        return countOfAddedProduct;
    }

    @Step("Collect product name")
    public String getProductName(int productPositionNumber) {
        productNameTextElement = (String.format(productNameTextElement, productPositionNumber));
        System.out.println("Collect product Name from page");
        waitUntilDisplayed(By.xpath(productNameTextElement), 5);
        String productName = waitUntilAttributeLoaded
                (driver.findElement(By.xpath(productNameTextElement)),
                        "innerText",
                        8).getAttribute("innerText");
        System.out.println("Product Name form the page is: " + productName);
        return productName;

    }

    @Step("Collect product Img. SRC")
    public String getProductImgSrc(int productPositionNumber) {
        productImgSrcElement = String.format(productImgSrcElement, productPositionNumber);
        waitUntilDisplayed(By.xpath(productImgSrcElement), 5);
        System.out.println("Collect product IMG src from page");
        String productImgSrc = waitUntilAttributeLoaded
                (driver.findElement(By.xpath(productImgSrcElement)),
                        "src",
                        6).getAttribute("src");
        System.out.println("Product ImgSRC from the page is: " + productImgSrc);
        return productImgSrc;
    }

    @Step("Collect product price")
    public String getProductPriceOnPage(int productPositionNumber) {
        productPriceTextElement = String.format(productPriceTextElement, productPositionNumber);
        waitUntilDisplayed(By.xpath(productPriceTextElement), 5);
        System.out.println("Collect product price from page");
        String productPriceFromPage = waitUntilAttributeLoaded
                (driver.findElement(By.xpath(productPriceTextElement)),
                        "textContent",
                        6).getAttribute("textContent");
        System.out.println("Product Price from the page is: " + productPriceFromPage);
        return productPriceFromPage;
    }

    public String getProductDescriptionTextInPopUp() {
        System.out.println("Collect product description in popUp");
        waitUntilDisplayed(itemDescriptionTextElementInPopUp, 5);
        String productDescriptionInPopUp = waitUntilAttributeLoaded
                (driver.findElement(itemDescriptionTextElementInPopUp),
                        "textContent",
                        12).getAttribute("textContent");
        System.out.println("Product description in the popUp is: " + productDescriptionInPopUp);
        return productDescriptionInPopUp;
    }

    public String getProductNameInThePopUp() {
        System.out.println("Collect product Name in popUp");
        waitUntilDisplayed(productNameInThePopUp, 5);
        String productNameInPopUp = waitUntilAttributeLoaded
                (driver.findElement(productNameInThePopUp),
                        "innerText",
                        12).getAttribute("innerText");
        System.out.println("Product name in the popUp is: " + productNameInPopUp);
        return productNameInPopUp;
    }

    public String getItemImgLinkInThePopUp() {
        System.out.println("Collect product img SRC in popUp");
        waitUntilDisplayed(productImgLinkInThePopUp, 5);
        String productImgSrcInPopUp = waitUntilAttributeLoaded
                (driver.findElement(productImgLinkInThePopUp),
                        "src",
                        12).getAttribute("src");
        System.out.println("Product Img Src in the popUp is: " + productImgSrcInPopUp);
        return productImgSrcInPopUp;
    }

    public String getProductPriceInThePopUp() {
        System.out.println("Collect product Price in popUp");
        waitUntilDisplayed(productPriceInThePopUp, 5);
        String productPriceInPopUp = waitUntilAttributeLoaded
                (driver.findElement(productPriceInThePopUp),
                        "innerText",
                        12).getAttribute("innerText");
        System.out.println("Product Price in the popUp is: " + productPriceInPopUp);
        return productPriceInPopUp;
    }

    public Boolean isOutOfStockErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//simple-snack-bar/span"), "We are out of stock"));
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public Boolean isYouCanOrderUpToOneItemErrorDisplayed() {
        try {
            return waitUntilDisplayed(youCanOrderUpToOneItemErrorMessage, 2).isDisplayed();
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
        System.out.println("actual Product description is: " + "'" + this.getProductDescriptionTextInPopUp() + "'");
        System.out.println("expected Product description is: " + "'" + productItemsDescription.getItemDescriptionByPositionNumber(productPositionNumber) + "'");
    }

    public void printActualAndExpectedProductDescription(String productName) {
        System.out.println("actual Product description is: " + "'" + this.getProductDescriptionTextInPopUp() + "'");
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
