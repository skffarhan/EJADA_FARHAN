package com.pageFactory.common.SAUCE;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class DashbordPage {
	private Pojo objPojo;

	public DashbordPage(Pojo pojo) {
		objPojo = pojo;
	}

	// Xpath
	private By cartLink 	= By.xpath("//a[@class='shopping_cart_link']");
	private By checkout 	= By.xpath("//button[@id='checkout']");
	private By firstNameID 	= By.xpath("//input[@id='first-name']");
	private By lastNameID 	= By.xpath("//input[@id='last-name']");
	private By zipCodeID 	= By.xpath("//input[@id='postal-code']");
	private By continueID 	= By.xpath("//input[@id='continue']");
	private By finishID 	= By.xpath("//button[@id='finish']");
	private By thankyouID 	= By.xpath("//h2[normalize-space()='Thank you for your order!']");
	private By dispatchID 	= By.xpath("//div[@class='complete-text']");

	public void selectProduct(String ProductName1, String ProductName2) {
		// Add to cart Product 1

		By locator1 = By.xpath("//div[normalize-space()='" + ProductName1 + "']");
		String Testdata = objPojo.getObjWrapperFunctions().getText(locator1, "text");
		objPojo.getObjUtilities().setDataPool("RuntimeGetProductName1", Testdata);

		By locator2 = By
				.xpath("//div[normalize-space()='" + ProductName1 + "']/following::button[text()='Add to cart'][1]");
		objPojo.getObjUtilities().logReporter("Click Add to cart for Product1. ",
				objPojo.getObjWrapperFunctions().click(locator2));

		// Add to cart Product 2
		By locator3 = By.xpath("//div[normalize-space()='" + ProductName2 + "']");
		Testdata = objPojo.getObjWrapperFunctions().getText(locator3, "text");
		objPojo.getObjUtilities().setDataPool("RuntimeGetProductName2", Testdata);

		By locator4 = By
				.xpath("//div[normalize-space()='" + ProductName2 + "']/following::button[text()='Add to cart'][1]");
		objPojo.getObjUtilities().logReporter("Click Add to cart for Product2.",
				objPojo.getObjWrapperFunctions().click(locator4));
	}

	public void ClickCart() {
		objPojo.getObjWrapperFunctions().waitFor(2);
		objPojo.getObjUtilities().logReporter("Click on Cart button. ",
				objPojo.getObjWrapperFunctions().click(cartLink));

	}

	public void verifyProducts() {
		// Check for Products 1
		By locator1 = By.xpath("(//*[@class='inventory_item_name'])[1]");
		String ProductName = objPojo.getObjWrapperFunctions().getText(locator1, "text");

		String appProductName1 = objPojo.getObjUtilities().dpString("RuntimeGetProductName1");

		if (appProductName1.equals(ProductName)) {
			System.out.println("Matching!!!");
			objPojo.getObjUtilities().logReporter("Values are Matching", appProductName1, ProductName, true);
		} else {
			System.out.println("MisMatch!!!");
			objPojo.getObjUtilities().logReporter("Values are Matching", appProductName1, ProductName, false);
		}

		// Check for Products 2
		By locator2 = By.xpath("(//*[@class='inventory_item_name'])[1]");
		ProductName = objPojo.getObjWrapperFunctions().getText(locator2, "text");

		String appProductName2 = objPojo.getObjUtilities().dpString("RuntimeGetProductName2");

		if (appProductName1.equals(ProductName)) {
			System.out.println("Matching!!!");
			objPojo.getObjUtilities().logReporter("Values are Matching", appProductName2, ProductName, true);
		} else {
			System.out.println("MisMatch!!!");
			objPojo.getObjUtilities().logReporter("Values are Matching", appProductName1, ProductName, false);
		}
	}

	public void CheckOut() {
		objPojo.getObjWrapperFunctions().waitFor(2);
		objPojo.getObjUtilities().logReporter("Click on Checkout button. ",
				objPojo.getObjWrapperFunctions().click(checkout));
		objPojo.getObjWrapperFunctions().waitFor(2);
	}

	public void setFirstnameInformation(String firstName) {
		// First Name
		objPojo.getObjUtilities().logReporter("Enter First Name", firstName,
				objPojo.getObjWrapperFunctions().setText(firstNameID, firstName));
	}

	public void setLastnameInformation(String LastName) {
		// Last Name
		objPojo.getObjUtilities().logReporter("Enter First Name", LastName,
				objPojo.getObjWrapperFunctions().setText(lastNameID, LastName));
	}

	public void setZipCodeInformation(String ZipCode) {
		// Zip Code
		objPojo.getObjUtilities().logReporter("Enter First Name", ZipCode,
				objPojo.getObjWrapperFunctions().setText(zipCodeID, ZipCode));
	}

	public void ClickContinue() {
		objPojo.getObjUtilities().logReporter("Click on Cart button. ",
				objPojo.getObjWrapperFunctions().click(continueID));
		objPojo.getObjWrapperFunctions().waitFor(2);
	}

	public void verifyFinalDetails(String titleName) {
		// Verify for Title
		By titleID = By.xpath("//span[@class='title']");
		String appTitle = objPojo.getObjWrapperFunctions().getText(titleID, "text");

		if (titleName.equals(appTitle)) {
			System.out.println("Matching!!!");
			objPojo.getObjUtilities().logReporter("Values for Title page Matching", titleName, appTitle, true);
		} else {
			System.out.println("MisMatch!!!");
			objPojo.getObjUtilities().logReporter("Values for Title page Matching", titleName, appTitle, false);
		}

		// Verify Total value
		By amountID = By.xpath("//span[@class='title']");
		String TotalAmount = objPojo.getObjWrapperFunctions().getText(titleID, "text");

		if (!TotalAmount.equals("")) {
			System.out.println("Matching!!!");
			objPojo.getObjUtilities().logReporter("Total Value before tax", TotalAmount, TotalAmount, true);
		} else {
			System.out.println("MisMatch!!!");
			objPojo.getObjUtilities().logReporter("Total Value before tax", TotalAmount, "", false);
		}

		String URL = "https://www.saucedemo.com/checkout-step-two.html";
		String appCurrntURL = objPojo.getCurrentURL();

		if (URL.equals(appCurrntURL)) {
			System.out.println("Matching!!!");
			objPojo.getObjUtilities().logReporter("Validate current URL", URL, appCurrntURL, true);
		} else {
			System.out.println("MisMatch!!!");
			objPojo.getObjUtilities().logReporter("Validate current URL", URL, appCurrntURL, false);
		}
	}

	public void ClickFinish() {
		objPojo.getObjUtilities().logReporter("Click on Finish button. ",
				objPojo.getObjWrapperFunctions().click(finishID));
		objPojo.getObjWrapperFunctions().waitFor(2);
	}

	public void verifyCompletionMessage(String thankYou, String dispatch) {

		// Verify Thank you message
		String data = objPojo.getObjWrapperFunctions().getText(thankyouID, "text");

		if (thankYou.equals(data)) {
			System.out.println("Matching!!!");
			objPojo.getObjUtilities().logReporter("Validate message as Thank you for your order", thankYou, data, true);
		} else {
			System.out.println("MisMatch!!!");
			objPojo.getObjUtilities().logReporter("Validate message as Thank you for your order", thankYou, data,
					false);
		}

		// Verify Thank you message
		data = objPojo.getObjWrapperFunctions().getText(dispatchID, "text");

		if (dispatch.equals(data)) {
			System.out.println("Matching!!!");
			objPojo.getObjUtilities().logReporter(
					"Validate message as Your order has been dispatched, and will arrive just as fast as the pony can get there!",
					dispatch, data, true);
		} else {
			System.out.println("MisMatch!!!");
			objPojo.getObjUtilities().logReporter(
					"Validate message as Your order has been dispatched, and will arrive just as fast as the pony can get there!",
					dispatch, data, false);
		}

		objPojo.getObjWrapperFunctions().waitFor(2);
	}
}
