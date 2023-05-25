package com.generics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * @ScriptName : Utilities
 * @Description : This class contains utilities function
 * @Author : Farhan Shaikh - AQM Technologies
 */
public class Utilities {
	private Pojo objPojo;

	public Utilities(Pojo pojo) {
		this.objPojo = pojo;
	}

	/**
	 * @Method : log Reporter SS for steps
	 * @Description : Reporter method
	 * @param : Step - Step description, resultLog - result log pass/fail
	 *          (true/false), includeMobile - result for mobile(true/false)
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public void logReporterScreenshot(String step, boolean resultLog) {
		String strLog = step;
		this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, resultLog - result log pass/fail
	 *          (true/false), includeMobile - result for mobile(true/false)
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	// @Step("{0}")

	public void logReporter(String step, boolean resultLog) {
		String strLog = step;
		this.addAssertTakeScreenShot(step, strLog, "", "", "", resultLog);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, inputValue - Input value, resultLog -
	 *          result log pass/fail (true/false), includeMobile - result for
	 *          mobile(true/false)
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	// @Step("{0} - {1}")
	public void logReporter(String step, String inputValue, boolean resultLog) {
		String strLog = step + " || Input Value : " + inputValue;
		this.addAssertTakeScreenShot(step, strLog, inputValue, "", "", resultLog);
	}

	/**
	 * @Method : logReporter
	 * @Description : Reporter method
	 * @param : Step - Step description, expectedValue - verification point expected
	 *          value, actualValue - verification point actual value, resultLog -
	 *          result log pass/fail (true/false), includeMobile - result for
	 *          mobile(true/false)
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	// @Step("{0} - {1} - {2}")
	public void logReporter(String step, String expectedValue, String actualValue, boolean resultLog) {
		String strLog = step + " || Expected Result : " + expectedValue + " || Actual Result : " + actualValue;
		this.addAssertTakeScreenShot(step, strLog, "", expectedValue, actualValue, resultLog);
	}

	/**
	 * @Method : addAssertTakeScreenShot
	 * @Description :
	 * @param :
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public void addAssertTakeScreenShot(String step, String strLog, String inputValue, String expectedValue,
			String actualValue, boolean resultLog) {
		System.out.println(objPojo.getTestCaseID() + "--> " + strLog);
		Logger logger = LoggerFactory.getLogger(Utilities.class);
		if (resultLog) {
			logger.info("Step Description--> " + objPojo.getTestCaseID() + "-->" + strLog);
			Assert.assertTrue(true, "Step Description--> " + objPojo.getTestCaseID() + strLog);
		} else {
			String fileName = getDateInSpecifiedFormat("dd_MMM_yyyy_HH_mm_ss") + "_TCID_" + objPojo.getTestCaseID()
					+ ".png";
			String fileWithPath = Pojo.reportPath + "\\target\\custom-reports\\surefire-reports\\ScreenShot\\"
					+ fileName;
			Reporter.log("Step Description--> " + strLog);
			logger.error("Step Description--> " + objPojo.getTestCaseID() + "-->" + strLog);
			logger.error("Step Description--> " + strLog);
			logger.error("Failure URl :-------------> " + objPojo.getDriver().getCurrentUrl());
			System.out.println("URl :-------------> " + objPojo.getDriver().getCurrentUrl());
			this.takeScreenShot(objPojo.getDriver(), fileWithPath);
			objPojo.setTestFailedStep(step);
			Assert.assertTrue(false, "Step Description--> " + step);
		}
	}

	/**
	 * @Method : takeScreenShot
	 * @Description : Take Screen shot for given web driver.
	 * @Author : Farhan Shaikh - AQM Technologies
	 * 
	 */
	public boolean takeScreenShot(WebDriver webDriver, String fileWithPath) {
		TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);
		try {
			FileUtils.moveFile(srcFile, destFile);
			this.fileToByte(destFile);
			return true;
		} catch (IOException iOException) {
			iOException.printStackTrace();
			return false;

		}
	}
	/**
	 * @Method : takeScreenShot for steps
	 * @Description : Take Screen shot for given web driver.
	 * @Author : Farhan Shaikh - AQM Technologies
	 * 
	 */

	boolean takeScreenShotSteps(WebDriver webDriver, String fileWithPath) {

		TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fileWithPath);

		try {
			FileUtils.moveFile(srcFile, destFile);
			this.fileToByte(destFile);

			return true;
		} catch (IOException iOException) {
			iOException.printStackTrace();
			return false;

		}

	}

	/**
	 * @Method : getDateInSpecifiedFormat
	 * @Description : This method takes parameter of your required DateFormat Type
	 *              Like: dd-mm-YYYY DD.MM.YYYY and in return it will give you
	 *              today's date in specified date format
	 * @param : dateFormat like : dd-MM-YYYY
	 * @Author : Farhan Shaikh - AQM Technologies
	 **/
	public String getDateInSpecifiedFormat(String dateFormat) {
		String current_date = "";
		Date today = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		current_date = formatter.format(today);
		return current_date;
	}

	/**
	 * @Method : fileToByte
	 * @Description : Converts image file to byte array for allure.
	 * @Author : Farhan Shaikh - AQM Technologies
	 * @throws : IOException
	 */
//	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] fileToByte(File file) throws IOException {
		if (file != null)
			return Files.readAllBytes(Paths.get(file.getPath()));
		else
			return new byte[0];
	}

	/**
	 * Generate random string
	 * 
	 * @return String random string value
	 */
	public String getRandomString(int lenght) {
		String allowedChars = "1234567890";
		String randomstring = "";
		for (int i = 0; i < lenght; i++) {
			int rnum = (int) Math.floor(Math.random() * allowedChars.length());
			randomstring += allowedChars.substring(rnum, rnum + 1);
		}
		return randomstring.toUpperCase();
	}

	/**
	 * @Method: dpString
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public String dpString(String columnHeader) {
		try {
			if (objPojo.getObjHashTable().get(columnHeader) == null)
				return "";
			else {
				return objPojo.getObjHashTable().get(columnHeader).trim();
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method: dpInteger
	 * @Description: this method returns data from the the previously loaded
	 *               datapool
	 * @param columnHeader - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Farhan Shaikh - AQM Technologies
	 * 
	 */
	public Integer dpInteger(String columnHeader) {
		try {
			if (objPojo.getObjHashTable().get(columnHeader) == null)
				return 0;
			else {
				String value = objPojo.getObjHashTable().get(columnHeader).trim();
				StringTokenizer strTokenizer = new StringTokenizer(value, ".");
				value = strTokenizer.nextToken();
				return Integer.parseInt(value);
			}
		} catch (NoSuchElementException exception) {
			return 0;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @Method: setDataPool
	 * @Description: this method returns data from the the previously loaded
	 *               setDataPool
	 * @param columnHeader - excel file header column name
	 * @return - value for corresponding header
	 * @Author : Farhan Shaikh - AQM Technologies
	 */
	public void setDataPool(String Key, String value) {
		try {
			objPojo.getObjHashTable().put(Key, value);
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	/**
	 * @author : Farhan Shaikh
	 * 
	 */

	public static String readLineByLineJava8(String file) {
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = java.nio.file.Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return contentBuilder.toString();
	}

	/**
	 * @author : Farhan Shaikh
	 * @return - ramdom values for Address
	 */
	public String randomEmailIdGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String address = null;

		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		char[] tempEightChars = characters.toCharArray();
		for (int i = 0; i < 8; i++) {
			char eightChars = tempEightChars[random.nextInt(tempEightChars.length)];
			sb.append(eightChars);
		}

		sb.append("@gmail.com");
		address = sb.toString();
		return address;

	}

	/**
	 * @author : Farhan Shaikh
	 * @return - Random Name generator
	 */
	public String getRandomFirstNameGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomname = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstEightChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char firstEightChars = tempFirstEightChars[random.nextInt(tempFirstEightChars.length)];
			sb.append(firstEightChars);
		}
		sb.append(" ");
		randomname = sb.toString();
		return randomname;

	}

	/**
	 * @author : Farhan Shaikh
	 * @return - Random Name generator
	 */
	public String getRandomLastNameGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomname = null;

		StringBuilder sb = new StringBuilder();

		char[] tempFirstEightChars = characters.toCharArray();
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			char firstEightChars = tempFirstEightChars[random.nextInt(tempFirstEightChars.length)];
			sb.append(firstEightChars);
		}
		sb.append(" ");
		randomname = sb.toString();
		return randomname;

	}

	/**
	 * @author : Farhan Shaikh
	 * @param : Sheet Name from the excel file
	 * @return - Load the test-data according to the scenario name.
	 * @throws IOException
	 */

	public synchronized void loadDataProvider(String fileName) throws IOException {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir") + "/src/test/resources/testData/TestData.xlsx";
			FileInputStream file = new FileInputStream(new File(testDataPath));
			workBook = new XSSFWorkbook(file);
			XSSFSheet sheet = workBook.getSheet(fileName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++) {
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(objPojo.getTestCaseID())) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							String value = sheet.getRow(j).getCell(i).getStringCellValue();
							if (!value.trim().equals(""))
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										sheet.getRow(j).getCell(i).getStringCellValue());
						} catch (IllegalStateException e) {
							try {
								objPojo.getObjHashTable().put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String readLineByJava8(String file) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = java.nio.file.Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
}