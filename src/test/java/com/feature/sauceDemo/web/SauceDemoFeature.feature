Feature: SaucesDemo TestCase

  Background: 
    Given Load "Sauce" TestData in Specific Excel Sheet"SauceDemo".
    When Navigate To Specific Portal : "WebURl"

  @ResourceName_Farhan @High  @P1 @smoke @TC_001_SauceDemo
  Scenario: TC_001_SauceDemo
    Then Fill "USERNAME" , "PASSWORD" and click on "Login" button in Sauce Login Page.
    Then Select DropDown For Shoting Price "Price (high to low)"
    Then Add the most expensive products to your cart "Sauce Labs Fleece Jacket" and "Sauce Labs Backpack"
    Then Click on the cart button
    Then Verify that selected products are displayed at the page
    Then Click on the Checkout button
   	Then Fill all the displayed form with "FIRSTNAME" , "LASTNAME" and "ZIPCODE"
   	Then Click on the Continue button
   	Then Verify the Final details with page title as "Checkout: Overview"
   	Then Click on the “Finish” button
   	Then Verify both, the Thank You as "Thank you for your order!" and the order dispatched messages as "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
   	   		
    
   	@ResourceName_Farhan @High  @P1 @smoke @TC_002_SauceDemo
   	 Scenario: TC_002_SauceDemo
    Then Fill "USERNAME" , "PASSWORD" and click on "Login" button in Sauce Login Page.
    Then Select DropDown For Shoting Price "Price (low to high)"
    Then Add the most expensive products to your cart "Sauce Labs Onesie" and "Sauce Labs Bike Light"
    Then Click on the cart button
    Then Verify that selected products are displayed at the page
    Then Click on the Checkout button
   	Then Fill all the displayed form with "FIRSTNAME" , "LASTNAME" and "ZIPCODE"
   	Then Click on the Continue button
   	Then Verify the Final details with page title as "Checkout: Overview"
   	Then Click on the “Finish” button
   	Then Verify both, the Thank You as "Thank you for your order!" and the order dispatched messages as "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
   	
   	@ResourceName_Farhan @High  @P1 @smoke @TC_003_SauceDemo
   	 Scenario: TC_003_SauceDemo
    Then Fill "USERNAME" , "PASSWORD" and click on "Login" button in Sauce Login Page.
    Then Select DropDown For Shoting Price "Name (Z to A)"
    Then Add the most expensive products to your cart "Test.allTheThings() T-Shirt (Red)" and "Sauce Labs Onesie"
    Then Click on the cart button
    Then Verify that selected products are displayed at the page
    Then Click on the Checkout button
   	Then Fill all the displayed form with "FIRSTNAME" , "LASTNAME" and "ZIPCODE"
   	Then Click on the Continue button
   	Then Verify the Final details with page title as "Checkout: Overview"
   	Then Click on the “Finish” button
   	Then Verify both, the Thank You as "Thank you for your order!" and the order dispatched messages as "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
   	
   	@ResourceName_Farhan @High  @P1 @smoke @TC_004_SauceDemo
   	 Scenario: TC_004_SauceDemo
    Then Fill "USERNAME" , "PASSWORD" and click on "Login" button in Sauce Login Page.
    Then Select DropDown For Shoting Price "Name (A to Z)"
    Then Add the most expensive products to your cart "Sauce Labs Backpack" and "Sauce Labs Bike Light"
    Then Click on the cart button
    Then Verify that selected products are displayed at the page
    Then Click on the Checkout button
   	Then Fill all the displayed form with "FIRSTNAME" , "LASTNAME" and "ZIPCODE"
   	Then Click on the Continue button
   	Then Verify the Final details with page title as "Checkout: Overview"
   	Then Click on the “Finish” button
   	Then Verify both, the Thank You as "Thank you for your order!" and the order dispatched messages as "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
   	
   	
   	 @ResourceName_Farhan @High  @P1 @smoke @TC_SauceDemo_Failed
  Scenario: TC_005_SauceDemo
    Then Fill "USERNAME" , "PASSWORD" and click on "Login" button in Sauce Login Page.
    Then Select DropDown For Shoting Price "Price (high to low)"
    Then Add the most expensive products to your cart "Sauce Labs Fleece Jacket" and "Sauce Labs Backpack"
    Then Click on the cart button
    Then Verify that selected products are displayed at the page
    Then Click on the Checkout button
   	Then Fill all the displayed form with "FIRSTNAME" , "LASTNAME" and "ZIPCODE"
   	Then Click on the Continue button
   	Then Verify the Final details with page title as "Checkout: Ove"
   	Then Click on the “Finish” button
   	Then Verify both, the Thank You as "Thank you for your order!" and the order dispatched messages as "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
   	   		