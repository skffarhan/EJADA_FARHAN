Feature: SaucesMobileDemo TestCase

  Background: 
    Given Load "Sauce" TestData in Specific Excel Sheet"SauceDemo-Mobile".

  @ResourceName_Shantanu @High @P1 @smoke 
  Scenario: TC_001_LoginDemo_Positive
    Then Fill "USERNAME" and "PASSWORD"
    And click on "LOGIN" button in Sauce Mobile Login
    And check weather the user logged in successfully

  @ResourceName_Shantanu @High @P2 @smoke
  Scenario: TC_002_LoginDemo_Negative
    Then Fill "USERNAME" and "PASSWORD"
    And click on "LOGIN" button in Sauce Mobile Login
    And check weather the user logged in successfully
