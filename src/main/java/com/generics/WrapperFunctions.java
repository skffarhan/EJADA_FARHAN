package com.generics;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import com.google.common.base.Function;

/**
 * @ScriptName : WrapperFunctions
 * @Description : Core wrapper function required for framework
 * @Author : Farhan Shaikh - AQM Technologies
 */
public class WrapperFunctions extends LoadableComponent<WrapperFunctions> {
	private Pojo objPojo;
	Boolean flag = false;
	private Properties objConfig; 

	public WrapperFunctions(Pojo pojo) {
		this.objPojo = pojo;

	}

	/**
	 * @Description : This is wrapper method wait for element presence located
	 * @param : locator - By identification of element
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public void waitForElementPresence(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * @Description : This is wrapper method wait for visibility of element located
	 * @param : locator - By identification of element
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public void waitForElementVisibilityLocated(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementVisibility(WebElement webElement) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
	}

	/**
	 * @Description : This is wrapper method wait for element to be clickable
	 * @param : locator - By identification of element
	 * @Author : Farhan Shaikh- AQM Technologies
	 */
	public void waitForElementToBeClickable(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * @Description : This is wrapper method wait for visibility of element located
	 * @param : locator - By identification of element
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public void waitForElementInVisibilityLocated(By locator) throws NotFoundException {
		objPojo.getWebDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/**
	 * @Description : This is wrapper method to check the web element is displayed
	 *              on the page
	 * @param : locator - By identification of element
	 * @return : - true if element present
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public boolean checkElementDisplayedWithoutWait(By locator) {
		try {
			return objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (Exception exception) {
			return false;
		}
	}

	public boolean openNewWindow() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("window.open('','_blank');");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean closeDriver() {
		try {
			objPojo.getDriver().close();
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/** @Author : Farhan Shaikh - AQM Technologies */
	public void waitAfterEachClick() {
		waitFor(objPojo.getAfterClickwait());
	}

	/**
	 * @Method : waitFor
	 * @Description : Waits for the specified amount of [timeInMilliseconds].
	 * @param : timeUnitSeconds - wait time seconds
	 * @Author : Farhan Shaikh- AQM Technologies
	 */
	public boolean waitFor(int timeUnitSeconds) {
		try {
			Thread.sleep(TimeUnit.MILLISECONDS.convert(timeUnitSeconds, TimeUnit.SECONDS));
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * Scroll element to view using javascript This script is used for scrolling
	 * down.
	 * 
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public boolean scrollToViewDown(By locator) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			((JavascriptExecutor) objPojo.getDriver()).executeScript("arguments[0].scrollIntoView(false);", webElement);
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotVisibleException");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : elementHighlight
	 * @Description : Highlight element
	 * @param : locator - By identification of element
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public void elementHighlight(By locator) {
		try {
			String MobileBrowser = objConfig.getProperty("mobile.application").trim().toLowerCase();
		
			WebElement element = objPojo.getDriver().findElement(locator);
			for (int i = 0; i < 1; i++) {
				if (MobileBrowser.equalsIgnoreCase("true"))
				{
					break;
				}
				else
				{
					JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: orange  ; border: 6px solid orange ;");
					waitFor(1);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
				}
			}
		} catch (Exception e) {
			//System.out.println("Element Highlighter is used before logger , Screenshot of failure not avialable");
		}
		
	}

	/**
	 * @Method : selectDropDownOption
	 * @Description : This is wrapper method select drop down element
	 * @param : locator - By identification of element
	 * @param : option - drop down element (user may specify text/value/index)
	 * @param : selectType - select dorp down element by Text/Value/Index
	 * @Author : Farhan Shaikh - AQM Technologies
	 */

	public boolean selectDropDownOption(By locator, String option, String... selectType) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			waitForElementVisibility(webElement);
			Select sltDropDown = new Select(webElement);
			if (selectType.length > 0 && !selectType[0].equals("")) {
				if (selectType[0].equalsIgnoreCase("Value"))
					sltDropDown.selectByValue(option);
				else if (selectType[0].equalsIgnoreCase("Text"))
					sltDropDown.selectByVisibleText(option);
				else if (selectType[0].equalsIgnoreCase("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));
				return true;
			} else {
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for (WebElement weOptions : options) {
					if (weOptions.getText().trim().equals(option)) {
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
						break;
					} else
						iIndex++;
				}
				return blnOptionAvailable;
			}

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	protected void load() {
		// Load method is implementation
	}

	@Override
	protected void isLoaded() throws Error {
		// isLoaded method is implementation witherror
	}

	/**
	 * @Author : Farhan Shaikh - AQM Technologies
	 * @Description : This method is for getting the text on the alert box.
	 */
	public String alertGetText() {
		Alert alert = objPojo.getDriver().switchTo().alert();
		return alert.getText();

	}

	/**
	 * @Method : click
	 * @Description : This is wrapper method to click on web element
	 * @param : WebElement -By identification of Locator
	 * @return : - true if click successful
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public boolean click(WebElement element) {
		try {
			waitForElementVisibility(element);
			element.click();
			waitAfterEachClick();
			return true;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		}
	}

	/**
	 * @Method : click
	 * @Description : This is wrapper method to click on web element
	 * @param : locator - By identification of element
	 * @return : - true if click successful
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	@SuppressWarnings("deprecation")
	public boolean click(By locator) {
		try 
		{			
			objPojo.getObjWrapperFunctions().elementHighlight(locator);
			waitForElementPresence(locator);
			waitForElementToBeClickable(locator);
			objPojo.getDriver().manage().timeouts().setScriptTimeout(objPojo.getScriptTimeoutWait(), TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
			js.executeScript("return arguments[0].click()", getElementFluent(locator));
			waitAfterEachClick();
			return true;

		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} 
		catch (Exception exception) 
		{
			try 
			{
				waitForElementPresence(locator);
				waitForElementToBeClickable(locator);
				getElementFluent(locator).click();
				waitAfterEachClick();
				return true;
			}
			catch (Exception exceptionJavascript) {
				objPojo.setCustomException("NoSuchElement Exception");
				return false;
			}
		}
	}

	public WebElement getElementFluent(final By locator) throws NoSuchElementException, TimeoutException {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(objPojo.getDriver())
				.withTimeout(Duration
						.ofSeconds(Integer.parseInt(objPojo.getObjConfig().getProperty("driver.WebDriverWait"))))
				.pollingEvery(Duration
						.ofSeconds(Integer.parseInt(objPojo.getObjConfig().getProperty("driver.WebDriverWait"))))
				.ignoring(NoSuchElementException.class).ignoring(InvalidElementStateException.class);

		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return objPojo.getDriver().findElement(locator);
			}
		});

		return webElement;

	}

	/**
	 * @Description : This is wrapper method to check the web element is displayed
	 *              on the page
	 * @param : locator - By identification of element
	 * @return : - true if element present
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public boolean checkElementDisplayed(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			return objPojo.getDriver().findElement(locator).isDisplayed();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Method : setText
	 * @Description : This is wrapper method to set text for input element
	 * @param : locator - By identification of element
	 * @param : fieldValue - field value as string
	 * @return : - true if text entered successfully
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public boolean setText(By locator, String fieldValue) {
		try 
		{
			objPojo.getObjWrapperFunctions().elementHighlight(locator);
			WebElement webElement = getElementFluent(locator);
			clearText(webElement);
			webElement.sendKeys(fieldValue);
			return true;
		}
		catch (NoSuchElementException exception) 
		{
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} 
		catch (TimeoutException exception) 
		{
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		}
		catch (Exception exception) 
		{
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}
	public void clearText(WebElement webElement) {
		webElement.clear();
	}

	/**
	 * @Method : selectCheckBox
	 * @Description : This is wrapper method select/deselect checkbox
	 * @param : locator - By identification of element
	 * @param : status - select/deselect
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public boolean selectCheckBox(By locator, boolean status) {
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			if (webElement.getAttribute("type").equals("checkbox")) {
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				if ((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					this.click(locator);
				return true;
			} else
				return false;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			return false;
		}
	}

	/**
	 * @Method : getText
	 * @Description : This is wrapper method to extract the text value of an
	 *              webelement : This method is used for dom ul/li/div/div/h4/span
	 * @param : locator - By identification of element
	 * @param : textBy - String - "value" or "text"
	 * @return : text value of the passed locator
	 * @Author : Farhan Shaikh - AQM Technologies
	 * 
	 */
	public String getText(By locator, String textBy) {
		String strText = "";
		try {
			WebElement element = objPojo.getDriver().findElement(locator);
			for (int i = 0; i < 1; i++) {
				JavascriptExecutor js = (JavascriptExecutor) objPojo.getDriver();
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: yellow  ; border: 6px solid yellow ;");
				waitFor(1);
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			}
			/////////////////
			waitForElementPresence(locator);
			waitForElementVisibilityLocated(locator);
			WebElement webElement = this.objPojo.getDriver().findElement(locator);
			scrollToViewDown(locator);
			switch (textBy.toLowerCase()) {
			case "value":
				strText = webElement.getAttribute("value");
				break;

			case "text":
				strText = webElement.getText();
				break;
			default:
				strText = webElement.getText();
				break;
			}
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
		}
		return strText;
	}

	/**
	 * @Description : This is wrapper method to check the web element is enabled on
	 *              the page
	 * @param : locator - By identification of element
	 * @return : - true if element present
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public boolean checkElementEnabled(By locator) {
		try {
			this.waitForElementVisibilityLocated(locator);
			this.waitForElementToBeClickable(locator);
			return objPojo.getDriver().findElement(locator).isEnabled();
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * @Method : Load Base URL
	 * @Description : Use Following method to Navigate to Specific URL
	 * @param : moduleName : Name of app path to be navigated
	 * 
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public void loadBaseURL() {
		String baseurl = objPojo.getObjConfig().getProperty("web.protocol") + "://"
				+ objPojo.getObjConfig().getProperty("web.domain");
		objPojo.getDriver().navigate().to(baseurl);
		objPojo.getObjUtilities().logReporter("Open URL - '" + baseurl + "'.", true);
	}

	/***
	 * 
	 * @Method : getAttribute
	 * @Description : This function return locator attribute*
	 * @param : locator - By identification of element*
	 * @author : Farhan Shaikh - AQM Technologies
	 */

	public String getAttribute(By locator, String sElementAttribute) {
		String strText = "";
		try {
			waitForElementPresence(locator);
			WebElement webElement = objPojo.getDriver().findElement(locator);
			strText = webElement.getAttribute(sElementAttribute);
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
		}
		return strText;
	}

	/**
	 * @Method : actionClick
	 * @Description : This is wrapper method click using mouse movement
	 * @param : locator - By identification of element
	 * @Author : Farhan Shaikh - AQM Technologies
	 */

	public boolean actionClick(final By locator) {
		try {
			this.waitForElementPresence(locator);
			this.waitForElementToBeClickable(locator);
			final WebElement webElement = this.objPojo.getDriver().findElement(locator);
			final Actions actionBuilder = new Actions(this.objPojo.getDriver());
			actionBuilder.click(webElement).build().perform();
			return true;
		} catch (NoSuchElementException exception) {
			this.objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception2) {
			this.objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception2.getMessage());
			exception2.printStackTrace();
			return false;
		} catch (NotFoundException exception3) {
			this.objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception3.getMessage());
			exception3.printStackTrace();
			return false;
		} catch (ElementNotInteractableException exception5) {
			this.objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception5.getMessage());
			exception5.printStackTrace();
		} catch (Exception exception7) {
			this.objPojo.setCustomException("NoSuchElement Exception");
			exception7.printStackTrace();
			return false;
		}
		return flag;
	}

	/**
	 * @Method : getSelectedValueFromDropDown
	 * @Description : This is wrapper method select drop down element
	 * @param : locator - By identification of element
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public String getSelectedValueFromDropDown(By locator) {
		try {
			waitForElementPresence(locator);
			Select selectDorpDown = new Select(objPojo.getDriver().findElement(locator));
			String selectedDorpDownValue = selectDorpDown.getFirstSelectedOption().getText();
			return selectedDorpDownValue;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (NotFoundException exception) {
			objPojo.setCustomException("NotFound Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (ElementNotInteractableException exception) {
			objPojo.setCustomException("ElementNotInteractableException Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return null;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return null;
		}
	}

	/**
	 * @Method : clearText
	 * @Description : This is wrapper method to clear value from locator
	 * @param : locator - By identification of element
	 * @Author : Farhan Shaikh - AQM Technologies
	 */

	public boolean clearText(By locator) {
		try {
			boolean flag = false;
			WebElement webElement = getElementFluent(locator);
			if (webElement.getTagName().equals("input")) {
				webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
				flag = true;
			}
			return flag;
		} catch (NoSuchElementException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got no such " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (TimeoutException exception) {
			objPojo.setCustomException("Timeout & NoSuchElement Exception");
			System.out.println("I got timeout " + exception.getMessage());
			exception.printStackTrace();
			return false;
		} catch (Exception exception) {
			objPojo.setCustomException("NoSuchElement Exception");
			exception.printStackTrace();
			return false;
		}
	}

	public List<String> locateElements(By locator) {
		ArrayList<String> tabs2 = new ArrayList<String>(objPojo.getDriver().getWindowHandles());
		objPojo.getDriver().switchTo().window(tabs2.get(1));
		objPojo.getDriver().manage().window().maximize();
		List<WebElement> elements = objPojo.getDriver().findElements(locator);
		List<String> elementText = new ArrayList<>();
		for (WebElement element : elements)
			elementText.add(element.getText());
		return elementText;
	}

	public List<WebElement> locateElements(By locator, String elementString) {
		System.out.println("elements locate : " + elementString);
		List<WebElement> elements = objPojo.getDriver().findElements(locator);
		return elements;
	}

	public String currentWindow() {
		String base = objPojo.getDriver().getWindowHandle();
		return base;
	}
}
