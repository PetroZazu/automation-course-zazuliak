package io.ctdev.lesson3;

public class LocatorsForProfileEditPopUp {
    private String nameCss
            = "div[class*='ownUserProfile_container'] input[name=name]";
    private String nameXpath
            = "//div[contains(@class, 'ownUserProfile_container')]//input[contains(@name, 'name')]";
   // знаходжу основний контейнер (ownUserProfile_container) і в даному контейнері шукаю поле 'input'

    private String nicknameCss
            = "div[class*='ownUserProfile_container'] input[name='login'][class*='editUserProfile']";
    private String nicknameXpath
            = "//div[contains(@class, 'ownUserProfile_container')]//input[contains(@name, 'login')and contains(@class, 'edit')]";
    // знаходжу основний контейнер (ownUserProfile_container) і в даному контейнері шукаю поле 'login' яке aka 'nickname'
    // також я вирішив добавити атрибут клас в пошук для елементу текстового поля

    private String PhoneNumberCss
            = "div[class*='ownUserProfile_container'] input[name=phoneNumber]";
    private String PhoneNumberXpath
            = "//div[contains(@class, 'ownUserProfile_container')]//input[contains(@name, 'phoneNumber')]";
    // знаходжу основний контейнер (ownUserProfile_container) і в даному контейнері шукаю елемент 'input' де name=phoneNumber

    private String positionCss
            = "div[class*='ownUserProfile_container'] input[name=position]";
    private String positionXpath
            = "//div[contains(@class, 'ownUserProfile_container')]//input[contains(@name, 'position')]";


    private String departmentCss
            = "div[class*='ownUserProfile_container'] input[name=department]";
    private String departmentXpath
            = "//div[contains(@class, 'ownUserProfile_container')]//input[contains(@name, 'department')]";




}
