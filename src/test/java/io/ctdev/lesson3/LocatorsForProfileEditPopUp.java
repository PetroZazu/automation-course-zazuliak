package io.ctdev.lesson3;

public class LocatorsForProfileEditPopUp {
    private String cssPathToProfileContainer = "div[class*='ownUserProfile_container']";
    private String xPathToProfileContainer = "//div[contains(@class, 'ownUserProfile_container')]";

    private String nameCss
            = cssPathToProfileContainer + " input[name=name]";
    private String nameXpath
            = xPathToProfileContainer + "//input[contains(@name, 'name')]";
    // знаходжу основний контейнер (ownUserProfile_container) і в даному контейнері шукаю поле 'input'

    private String nicknameCss
            = cssPathToProfileContainer + " input[name='login'][class*='editUserProfile']";
    private String nicknameXpath
            = xPathToProfileContainer + "//input[contains(@name, 'login') and contains(@class, 'edit')]";
    // знаходжу основний контейнер (ownUserProfile_container) і в даному контейнері шукаю поле 'login' яке aka 'nickname'
    // також я вирішив добавити атрибут клас в пошук для елементу текстового поля

    private String PhoneNumberCss
            = cssPathToProfileContainer + " input[name=phoneNumber]";
    private String PhoneNumberXpath
            = xPathToProfileContainer + "//input[contains(@name, 'phoneNumber')]";

    private String positionCss
            = cssPathToProfileContainer + " input[name=position]";
    private String positionXpath
            = xPathToProfileContainer + "//input[contains(@name, 'position')]";

    private String departmentCss
            = cssPathToProfileContainer + " input[name=department]";
    private String departmentXpath
            = xPathToProfileContainer + "//input[contains(@name, 'department')]";

    private String cancelButtonCss
            = cssPathToProfileContainer + " button[type=button]";
    private String cancelButtonXpath
            = xPathToProfileContainer + "//button[contains(@type, 'button') and contains(.,'Cancel')]";


}
