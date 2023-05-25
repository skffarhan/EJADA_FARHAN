package com.pageFactory.Sauce.Mobile;

import org.openqa.selenium.By;

import com.generics.Pojo;

public class LoginPage_Mobile {
	private Pojo objPojo;

	public LoginPage_Mobile(Pojo pojo) {
		objPojo = pojo;
	}
	private By inpUserName = By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
	private By inpPassword = By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
	
	public void setUsername(String value) {
		objPojo.getObjUtilities().logReporter("Enter Username in Sauce Login Page", value,
				objPojo.getObjWrapperFunctions().setText(inpUserName, value));
	}
	public void setPassword(String value) {
		objPojo.getObjUtilities().logReporter("Enter Password in Sauce Login Page", value,
				objPojo.getObjWrapperFunctions().setText(inpPassword, value));
	}

}
