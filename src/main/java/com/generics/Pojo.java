package com.generics;

import java.util.Hashtable;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Stopwatch;

/*
 * This is the POJO class for the getter and setters.
 */

public class Pojo {

	private WebDriver webDriver = null;
	private WebDriverWait webDriverWait;
	private Properties objConfig;
	private Hashtable<String, String> dataPoolHashTable;
	private Utilities objUtilities;
	private WrapperFunctions objWrapperFunctions;
	private String runningScript = "";
	private String testCaseID = null;
	private String Description = null;
	private int runID;
	private int afterClickwait = 0;
	private int scriptTimeoutWait = 0;
	private boolean executionFlag;
	private String exceptionFlag = "";
	private int testDataRow;
	private String portalURL = "";
	private Hashtable<String, String> objHashTable = new Hashtable<String, String>();
	private XSSFWorkbook workbook;
	private String TCID;
	public static String reportPath = System.getProperty("user.dir");
	private XSSFWorkbook dataValidator;
	private String step;
	private Stopwatch stopwatch;
	private String executionTimeStamp = "";

	public int getScriptTimeoutWait() {
		return this.scriptTimeoutWait;
	}

	public void setScriptTimeoutWait(int scriptTimeoutWait) {
		this.scriptTimeoutWait = scriptTimeoutWait;
	}
	public Integer getAfterClickwait() {
		return this.afterClickwait;
	}

	public void setAfterClickwait(Integer afterClickwait) {
		this.afterClickwait = afterClickwait;
	}
	public void setDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getDriver() {
		return this.webDriver;
	}

	public void setWebDriverWait(WebDriverWait webDriverWait) {
		this.webDriverWait = webDriverWait;
	}

	public WebDriverWait getWebDriverWait() {
		return this.webDriverWait;
	}

	public void setObjConfig(Properties objConfig) {
		this.objConfig = objConfig;
	}

	public Properties getObjConfig() {
		return this.objConfig;
	}

	public void setDataPoolHashTable(Hashtable<String, String> dataPoolHashTable) {
		this.dataPoolHashTable = dataPoolHashTable;
	}

	public Hashtable<String, String> getDataPoolHashTable() {
		return this.dataPoolHashTable;
	}

	public void setRunningScriptName(String scriptName) {
		this.runningScript = scriptName;
	}

	public String getRunningScriptName() {
		return this.runningScript;
	}

	public Utilities getObjUtilities() {
		return this.objUtilities;
	}

	public void setObjUtilities(Utilities objUtilities) {
		this.objUtilities = objUtilities;
	}

	public WrapperFunctions getObjWrapperFunctions() {
		return this.objWrapperFunctions;
	}

	public void setObjWrapperFunctions(WrapperFunctions objWrapperFunctions) {
		this.objWrapperFunctions = objWrapperFunctions;
	}

	public String getTestCaseID() {
		return this.testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public boolean getExecutionFlag() {
		return this.executionFlag;
	}

	public void setExecutionFlag(boolean executionFlag) {
		this.executionFlag = executionFlag;
	}

	public int getRunID() {
		return this.runID;
	}

	public void setRunID(int runID) {
		this.runID = runID;
	}

	public String getCustomException() {
		return exceptionFlag;
	}

	public void setCustomException(String exceptionFlag) {
		this.exceptionFlag = exceptionFlag;
	}

	public int getTestDataRow() {
		return testDataRow;
	}

	public void setTestDataRow(int testDataRow) {
		this.testDataRow = testDataRow;
	}

	public String getPortalURL() {
		return portalURL;
	}

	public void setPortalURL(String portalURL) {
		this.portalURL = portalURL;
	}

	public void setRunningTestName(String methodName) {
		TCID = methodName;
	}

	public String getRunninTestName() {
		return TCID;
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public Hashtable<String, String> getObjHashTable() {
		return objHashTable;
	}

	public void setObjHashTable(Hashtable<String, String> objHashTable) {
		this.objHashTable = objHashTable;
	}

	public XSSFWorkbook getDataValidator() {
		return dataValidator;
	}

	public void setDataValidator(XSSFWorkbook dataValidator) {
		this.dataValidator = dataValidator;
	}

	void setTestFailedStep(String step) {
		this.step = step;
	}

	public String getTestFailedStep() {
		return step;
	}

	public Stopwatch getStopwatch() {
		return stopwatch;
	}

	public void setStopwatch(Stopwatch stopwatch) {
		this.stopwatch = stopwatch;
	}

	public String getExecutionTimeStamp() {
		return executionTimeStamp;
	}

	public void setExecutionTimeStamp(String executionTimeStamp) {
		this.executionTimeStamp = executionTimeStamp;
	}
	
	public String getCurrentURL() {
		return this.webDriver.getCurrentUrl();
	}

}