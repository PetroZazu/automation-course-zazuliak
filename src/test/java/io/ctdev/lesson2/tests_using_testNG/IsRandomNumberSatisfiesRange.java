package io.ctdev.lesson2.tests_using_testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class IsRandomNumberSatisfiesRange {
    int minVal = 0;
    int maxVal = 10;
    Random rand = new Random();
    int random = rand.nextInt(15);

    @Test
    public void verifyRandNumber() {
        System.out.println(random);
        Assert.assertTrue(random >= minVal && random <= maxVal);
    }
}
