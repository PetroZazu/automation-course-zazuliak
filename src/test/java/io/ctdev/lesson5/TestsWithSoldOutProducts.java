package io.ctdev.lesson5;

import io.ctdev.lesson4.juiceshophw.BaseTestJuiceShop;
import io.ctdev.lesson5.preconditions.AccountActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestsWithSoldOutProducts extends BaseTestJuiceShop {
    @BeforeClass
    public void beforeClass2() {
        AccountActions.logInToTheAccount(driver);
    }

    @Test
    public void verifyInabilityAddSoldOutProductToCart() {
        scrollToTheElement(By.xpath("//button[contains(@aria-label, 'Next page')]"));
        goToTheNextPage();
        addSoldOutProduct();
        verifySoldOutProductMessage();
    }

    public void addSoldOutProduct() {
        waitUntilDisplayed(By.xpath
                ("(//mat-card//span[contains(text(), 'Sold Out')]/../..//span[contains(text(), 'Add to Basket')])[1]"), 3).click();
    }

    public void goToTheNextPage() {
        waitUntilDisplayed(By.xpath("//button[contains(@aria-label, 'Next page')]"), 3).click();
    }

    public void scrollToTheElement(By locator) {
        System.out.println("Performing scroll to the element: " + locator);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(locator));

    }

    public void verifySoldOutProductMessage() {
        Assert.assertTrue(waitUntilDisplayed(By.xpath("//span[contains(text(), 'We are out of stock')]"), 3).isDisplayed());
    }

}

