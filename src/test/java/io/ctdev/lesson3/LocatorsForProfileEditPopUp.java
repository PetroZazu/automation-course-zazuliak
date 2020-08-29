package io.ctdev.lesson3;

public class LocatorsForProfileEditPopUp {
    private String nameCss
            = "div[class*='ownUserProfile_container'] [name=name]";
    private String nameXpath
            = "//div[contains(@class, 'ownUserProfile_container')] //input[contains(@name, 'name')]";
}
