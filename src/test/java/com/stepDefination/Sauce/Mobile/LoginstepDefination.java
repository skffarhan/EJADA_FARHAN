package com.stepDefination.Sauce.Mobile;

import org.openqa.selenium.By;

import com.generics.BaseTest;
import com.generics.Pojo;
import com.pageFactory.Sauce.Mobile.LoginPage_Mobile;
import io.cucumber.java.en.Then;

public class LoginstepDefination {

	private Pojo objPojo;
	private String testData;
	private LoginPage_Mobile objLoginPage;


	public LoginstepDefination(BaseTest pojo) throws Exception {
		objPojo = pojo.initializeWebEnvironment();
		objLoginPage = new LoginPage_Mobile(objPojo);
	}

	/**
	 * @ScriptName : Fill Username and password
	  * @Author : Farhan Shaikh
	 **/
	@Then("Fill {string} and {string}")
	public void fill_and(String username, String password) {
		
		//Set Username
		
		testData = objPojo.getObjUtilities().dpString("USERNAME");
		if (!testData.equals("")) {
			objLoginPage.setUsername(testData);
		}				

		//Set Password		
		testData = objPojo.getObjUtilities().dpString("PASSWORD");		
		if (!testData.equals("")) {
			objLoginPage.setPassword(testData);
		}	

	}
	/**
	 * @ScriptName : Click on Login Button
	 * @Author : Farhan Shaikh
	 **/
	@Then("click on {string} button in Sauce Mobile Login")
	public void click_on_button_in_sauce_mobile_login(String login) {
		By locator = By.xpath("//android.view.ViewGroup[@content-desc='test-"+login+"']");
		objPojo.getObjUtilities().logReporter("Click Login Button to get logged in Mobile Application.",
				objPojo.getObjWrapperFunctions().click(locator));
		objPojo.getObjWrapperFunctions().waitFor(2);
		
	    
	}
	/**
	 * @ScriptName : Verify method
	 * @Author : Farhan Shaikh
	 **/
	@Then("check weather the user logged in successfully")
	public void check_weather_the_user_logged_in_successfully() {
	   
		By negative = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]");
	//	String objPojo.getObjWrapperFunctions().getText(negative, "text");
		if(objPojo.getObjWrapperFunctions().checkElementDisplayed(negative)) {
			objPojo.getObjUtilities().logReporter("Invalid credentials", true);
			
		}
		else
		{
			objPojo.getObjUtilities().logReporter("Successfully login", true);
			By Menu = By.xpath("//android.view.ViewGroup[@content-desc=\"test-Menu\"]");
			objPojo.getObjWrapperFunctions().click(Menu);
			By Logout = By.xpath("(//android.widget.TextView[@content-desc=\"test-Price\"])[1]");
			objPojo.getObjWrapperFunctions().click(Logout);
			
			
		}
	}
}
