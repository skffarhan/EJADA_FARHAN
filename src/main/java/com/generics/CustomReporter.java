package com.generics;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @ScriptName : CustomeReporter
 * @Description : TestNG provides the @Listeners annotation which listens to
 *              every event that occurs in a selenium code.
 * @Author : Farhan Shaikh.
 */

public class CustomReporter implements ITestListener {

	
	@Override
	public void onTestStart(ITestResult result) {
		// An onTestStart() is invoked only when any test method gets started.
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// An onTestSuccess() method is executed on the success of a test method.

	}

	@Override
	public void onTestFailure(ITestResult result) {


	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// An onTestSkipped() run only when any test method has been skipped.
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// This method is invoked each time when the test method fails but within
		// success percentage
	}

	@Override
	public void onStart(ITestContext context) {

		// An onStart() method is executed on the start of any test method.
	}

	@Override
	public void onFinish(ITestContext context) {
		// An onFinish() is invoked when any test case finishes its execution.
		
	}
}