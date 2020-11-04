Feature: Customer Registration

  Scenario: User is able to create new account
    Given user opens signIn page
    When User provides uniqueRandomEmail and password "superPass12" and repeatPassword "superPass12" and securityQuestion "secQueston"
    And User clicks on the Submit button
    Then User should create new account for himself
