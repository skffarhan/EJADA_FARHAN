package com.stepDefination.Common;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.generics.BaseTest;
import com.generics.Pojo;
import com.pageFactory.common.SAUCE.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonConfiqurationStepDefination {

	private Pojo objPojo;
	private String testData;
	private LoginPage objLoginPage;

	public CommonConfiqurationStepDefination(BaseTest pojo) throws Exception {
		objPojo = pojo.initializeWebEnvironment();
		objLoginPage = new LoginPage(objPojo);
	}

	@Before
	public void initializeScenario(Scenario scenario) {
		objPojo.setTestCaseID(scenario.getName());
	}

	@After
	public void tearDown(Scenario scenario) {
		((BaseTest) objPojo).tearDownWebEnvironment(scenario);
	}

	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {
		byte[] screenshotAs = ((TakesScreenshot) objPojo.getDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshotAs, "image/png", scenario.getName());
	}

	/**
	 * @Method : Load URL
	 * @Description : Use Following method to Navigate to Specific URL
	 * @param : moduleName : Name of app path to be navigated
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	@When("Navigate To Specific Portal : {string}")
	public void navigate_To_Specific_Portal(String string) {
		objPojo.getObjWrapperFunctions().loadBaseURL();
	}

	/**
	 * @throws IOException
	 * @Method : Load Data Provider
	 * @Description : Use Following method to Get excel data
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	@Given("Load {string} TestData in Specific Excel Sheet{string}.")
	public void load_TestData_in_Specific_Excel_Sheet(String string, String SheetName) throws IOException {
		objPojo.getObjUtilities().loadDataProvider(SheetName);
		System.err.println("Test data Load for execution>>>>" + objPojo.getObjHashTable());
	}

	/**
	 * @ScriptName : Method Login user name and password
	 * @Author : Farhan Shaikh.
	 **/

	@Then("Fill {string} , {string} and click on {string} button in Sauce Login Page.")
	public void fill_and_click_on_button_in_uti_login_page(String UserName, String Password, String Login) {

		// Set User Name
		testData = objPojo.getObjUtilities().dpString("USERNAME");
		if (!testData.equals("")) {
			objLoginPage.setUsername(testData);

		}

		// Set Password
		testData = objPojo.getObjUtilities().dpString("PASSWORD");
		if (!testData.equals("")) {
			objLoginPage.setPassword(testData);
		}

		// Click Login
		By locator = By.xpath("//input[@id='login-button']");
		objPojo.getObjUtilities().logReporter("Click Login Button to get logged in. ",
				objPojo.getObjWrapperFunctions().click(locator));
		objPojo.getObjWrapperFunctions().waitFor(2);

		// Click OK on popup

		By locator1 = By.xpath("//div[@id='root']");
		objPojo.getObjUtilities().logReporter("Click ok on Pop after Login. ",
				objPojo.getObjWrapperFunctions().click(locator1));
		objPojo.getObjWrapperFunctions().waitFor(2);

	}

}
