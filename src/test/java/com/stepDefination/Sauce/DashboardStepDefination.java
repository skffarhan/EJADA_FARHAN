package com.stepDefination.Sauce;

import org.openqa.selenium.By;

import com.generics.BaseTest;
import com.generics.Pojo;
import com.pageFactory.common.SAUCE.DashbordPage;

import io.cucumber.java.en.Then;

public class DashboardStepDefination {

	private Pojo objPojo;
	private DashbordPage objDashbordPage;
	private String testData;

	public DashboardStepDefination(BaseTest pojo) throws Exception {
		objPojo = pojo.initializeWebEnvironment();
		objDashbordPage = new DashbordPage(objPojo);

	}

	@Then("Select DropDown For Shoting Price {string}")
	public void select_drop_down_for_shoting_price(String Dropdown) {
		By locator2 = By.xpath("//select[@class='product_sort_container']");
		objPojo.getObjWrapperFunctions().selectDropDownOption(locator2, Dropdown);
		objPojo.getObjWrapperFunctions().waitFor(2);
	}

	@Then("Add the most expensive products to your cart {string} and {string}")
	public void add_the_most_expensive_products_to_your_cart(String ProductName1, String ProductName2) {
		if (!ProductName1.equals("") && !ProductName2.equals("")) {
			objDashbordPage.selectProduct(ProductName1, ProductName2);
		}

	}

	@Then("Click on the cart button")
	public void click_on_the_cart_button() {
		objDashbordPage.ClickCart();
	}

	@Then("Verify that selected products are displayed at the page")
	public void verify_that_selected_products_are_displayed_at_the_page() {
		objDashbordPage.verifyProducts();
	}

	@Then("Click on the Checkout button")
	public void click_on_the_checkout_button() {
		objDashbordPage.CheckOut();
	}

	@Then("Fill all the displayed form with {string} , {string} and {string}")
	public void fill_all_the_displayed_form_with_and(String string, String string2, String string3) {
		testData = objPojo.getObjUtilities().dpString("FIRST-NAME");
		if (!testData.equals("")) {
			String FirstName = objPojo.getObjUtilities().getRandomFirstNameGenerator();
			objDashbordPage.setFirstnameInformation(FirstName);
		}
		testData = objPojo.getObjUtilities().dpString("LAST-NAME");
		if (!testData.equals("")) {
			String LastName = objPojo.getObjUtilities().getRandomLastNameGenerator();
			objDashbordPage.setLastnameInformation(LastName);
		}
		testData = objPojo.getObjUtilities().dpString("ZIP-CODE");
		if (!testData.equals("")) {
			String ZipCode = objPojo.getObjUtilities().getRandomString(6);
			objDashbordPage.setZipCodeInformation(ZipCode);
		}
	}

	@Then("Click on the Continue button")
	public void click_on_the_continue_button() {
		objDashbordPage.ClickContinue();
	}

	@Then("Verify the Final details with page title as {string}")
	public void verify_the_final_details_with_page_title_as(String string) {
		objDashbordPage.verifyFinalDetails(string);
	}

	@Then("Click on the “Finish” button")
	public void click_on_the_finish_button() {
		objDashbordPage.ClickFinish();
	}

	@Then("Verify both, the Thank You as {string} and the order dispatched messages as {string}")
	public void verify_both_the_thank_you_as_and_the_order_dispatched_messages_as(String string, String string2) {
		objDashbordPage.verifyCompletionMessage(string, string2);
	}

}
