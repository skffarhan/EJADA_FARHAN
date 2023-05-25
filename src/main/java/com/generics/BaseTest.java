package com.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Stopwatch;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class BaseTest extends Pojo {
	private Properties objConfig;

	/*
	 * This is a POJO class for initializing the web environment without the config
	 * properties.
	 */

	public Pojo initializeWebEnvironment() {
		if (this.getDriver() == null) {

			this.setObjUtilities(new Utilities(this));
			this.setExecutionTimeStamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			this.setStopwatch(Stopwatch.createStarted());
			this.loadConfigProperties();

			this.setDriver(initializeWebEnvironment(this.objConfig));

			this.setAfterClickwait(Integer.parseInt(this.objConfig.getProperty("AfterClickWait")));
			this.setScriptTimeoutWait(Integer.parseInt(this.objConfig.getProperty("ScriptTimeoutWait")));
			this.setWebDriverWait(new WebDriverWait(this.getDriver(),
					Duration.ofSeconds(Integer.parseInt(this.objConfig.getProperty("driver.WebDriverWait").trim()))));
			this.setObjWrapperFunctions(new WrapperFunctions(this));
		}
		return this;
	}

	/*
	 * This is a POJO class tear-down class for web environment.
	 */

	public void tearDownWebEnvironment(Scenario scenario) {
		try {
			this.getDriver().quit();
			setObjUtilities(null);
			this.getObjWrapperFunctions().waitFor(1);
			System.out.println("Kill Chrome Browser.!!!!!!");
			this.getStopwatch().stop();
		} catch (Exception var4) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} catch (IOException var3) {
				var3.printStackTrace();
			}
			var4.printStackTrace();
			if (System.getProperty("web.browser").trim().equalsIgnoreCase("IE")
					|| System.getProperty("web.browser").trim().equalsIgnoreCase("Chrome")) {
				this.killBrowserAndDriver(this.objConfig);
			}
		}
		this.setObjWrapperFunctions(null);

	}

	/*
	 * This is a POJO class for utilizing the Config properties.
	 */

	private void loadConfigProperties() {
		try {
			this.objConfig = new Properties();
			this.objConfig.load(
					new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties"));
			this.setObjConfig(this.objConfig);
		} catch (Exception var2) {
			var2.printStackTrace();
		}
	}

	/*
	 * This is a POJO class killing the browser and driver
	 */

	@After
	private void killBrowserAndDriver(Properties objConfig) {
		this.getDriver().quit();

	}

	/*
	 * This is a POJO class for initializing the web environment with the config
	 * properties.
	 */

	private WebDriver initializeWebEnvironment(Properties objConfig) {
		WebDriver webDriver = null;
		AndroidDriver mobileDriver = null;

		try {
			String browser = objConfig.getProperty("web.browser").trim().toLowerCase();
			String HeadlessBrowser = objConfig.getProperty("Headless.browser").trim().toLowerCase();
			String MobileBrowser = objConfig.getProperty("mobile.application").trim().toLowerCase();

			if (MobileBrowser.equalsIgnoreCase("true")) {
				String GRIDurl = objConfig.getProperty("MobileGridURL");
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("platformName", objConfig.getProperty("MobilePlatform"));
				cap.setCapability("platformVersion", objConfig.getProperty("MobilePlatformVersion"));
				cap.setCapability("deviceName", objConfig.getProperty("MobileDeviceName"));
				cap.setCapability("appPackage", objConfig.getProperty("MobileApplicationPackage"));
				cap.setCapability("appActivity", objConfig.getProperty("MobileApplicationActivity"));
				cap.setCapability("noReset", objConfig.getProperty("MobileNoReset"));
				cap.setCapability("autoAcceptAlerts", objConfig.getProperty("MobileAllowpopups"));
				cap.setCapability("autoDissmissAlerts", objConfig.getProperty("MobileDenypopups"));
				mobileDriver = new AndroidDriver(new URL(GRIDurl), cap);

				return mobileDriver;
			}

			if (browser.equalsIgnoreCase("chrome") && (objConfig.getProperty("webDriver").equalsIgnoreCase("true"))) {
				ChromeOptions objChromeOptions = new ChromeOptions();
				if (objConfig.getProperty("chromeIncognito").equalsIgnoreCase("true"))
					objChromeOptions.addArguments("incognito");
				if (objConfig.getProperty("webDriver").equalsIgnoreCase("true")) {
					objChromeOptions.addArguments("--remote-allow-origins=*");		
					System.setProperty("webdriver.chrome.driver",
							System.getProperty("user.dir") + objConfig.getProperty("webdriver.chrome.driver").trim());
					webDriver = new ChromeDriver(objChromeOptions);

				}
				objChromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
				objChromeOptions.setExperimentalOption("prefs", prefs);
			}
			if (HeadlessBrowser.equalsIgnoreCase("true")) {
				System.setProperty("webdriver.chrome.driver", objConfig.getProperty("webdriver.chrome.driver").trim());
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--remote-allow-origins=*");
				webDriver = new ChromeDriver(chromeOptions);

			} 
			else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + objConfig.getProperty("webdriver.firefox.driver").trim());
				FirefoxOptions ffoptions = new FirefoxOptions();
				File pathToFirefoxBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				FirefoxBinary ffBinary = new FirefoxBinary(pathToFirefoxBinary);
				ffoptions.setBinary(ffBinary);
				webDriver = new FirefoxDriver(ffoptions);
			}
			
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(
					Duration.ofSeconds(Integer.parseInt(objConfig.getProperty("driver.implicitlyWait").trim())));
			webDriver.manage().timeouts().pageLoadTimeout(
					Duration.ofSeconds(Integer.parseInt(objConfig.getProperty("driver.pageLoadTimeout").trim())));

			return webDriver;
		} catch (Exception var4) {
			var4.printStackTrace();
			return null;
		}
	}

}