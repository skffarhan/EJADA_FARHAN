package com.stepDefination.API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import com.generics.BaseTest;
import com.generics.Pojo;
import com.generics.Utilities;
import com.pageFactory.API.jsonPage;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DummyApistepDefination {
	private String BaseURI;
	private Pojo objPojo;
	private Hashtable<String, String> objHashTable = new Hashtable<String, String>();
	private String BaseUri;
	private File filepath;
	private Response response;
	RequestSpecification httprequest;
	private String testData;
	String JsonPath = (System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\");
	private io.restassured.path.json.JsonPath jsonPathEvaluator;
	private jsonPage objjsonPage;
	private String TCID = "";

	public DummyApistepDefination(BaseTest pojo) throws Exception {
		objPojo = pojo.initializeWebEnvironment();
		objjsonPage = new jsonPage(objPojo);

	}

	@Before 
	public void initializeScenario(Scenario scenario) {
		TCID = scenario.getName();

	}

	@Given("I am requesting {string}")
	public void i_am_requesting(String arg1) {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);

		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		BaseUri = prop.getProperty(arg1);
		System.out.println(BaseUri);
	}

	@Then("I go to {string} and {string} Api Request Saved in Json File {string} without charset")
	public void _I_go_to_and_Api_Request_Saved_in_Json_File_without_charset(String string, String string2,
			String string3) {
		String jsonfilepath = System.getProperty("user.dir") + "/src/test/resources/ApiJsonData//" + string3;
		String file = Utilities.readLineByJava8(jsonfilepath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		httprequest = RestAssured.given().config(config).header("Content-Type", "application/json")
				.body(file).log().all();
		String P = "POST";
		String P1 = "PUT";
		String D = "DELETE";
		String G = "GET";
		if (P.equalsIgnoreCase(string2)) {
			response = httprequest.post(BaseUri + string);
		}
		if (G.equalsIgnoreCase(string2)) {
			response = httprequest.get(BaseUri + string);
		}
		System.out.print(response.getStatusCode());
		System.out.print(response.headers());
		System.out.print("Reponse is : " + response.asString());
		jsonPathEvaluator = response.jsonPath();
	}

	@Then("Save Final Response in {string} output File.")
	public void save_Final_Response_in_output_File(String string) throws IOException {
		FileWriter file = new FileWriter(JsonPath + string);
		System.out.println(response.asString());
		String json = response.asString();
		System.out.println(json);
		file.write(json);
		file.flush();
		file.close();

	}

	@Given("Base Test {string} data replace with RunTime test Data {string}")
	public void base_Test_data_Replace_with_RunTime_Test_Data(String string, String string2) throws IOException {
		String DynamicfilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\";
		String Filepath1 = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\";
		filepath = new File(DynamicfilePath + string);
		FileInputStream FileRead = new FileInputStream(filepath);
		FileOutputStream FileWrite = new FileOutputStream(Filepath1 + string2);
		System.out.print("File is copied");
		int c;
		while ((c = FileRead.read()) != -1)
			FileWrite.write((char) c);
		FileRead.close();
		FileWrite.close();
	}

	@Then("I am validating object {string} for string value {string} For {string}.")
	public void i_am_validating_object_for_string_value_For(String name, String data, String Module) {
		String value = jsonPathEvaluator.get(name);
		System.out.println(value);
		Assert.assertEquals(data, value.trim());
		objPojo.getObjUtilities().logReporter("Verify the response value in " + Module + "Api", data, value.trim(),
				value.trim().equals(data));
	}

	@Then("Create Input File for json from Excel Sheet")
	public void create_Input_File_for_json_from_Excel_Sheet() throws IOException {
		testData = objHashTable.get("EMAIL-ID");
		if (!testData.equals("")) {
			String Randomemailid = objPojo.getObjUtilities().randomEmailIdGenerator();
			objjsonPage.readrequiresdataromjsoninputfile(Randomemailid);
		}
		testData = objHashTable.get("CUSTOMER-NAME");
		if (!testData.equals("")) {
			String RandomName = objPojo.getObjUtilities().getRandomFirstNameGenerator();
			objjsonPage.readrequiresdataromjsoninputfileforrandomname(RandomName);
		}

	}

	@Given("Load Testdata form Specific Api sheet {string}")
	public void load_testdata_form_specific_api_sheet(String SheetName) throws IOException {
		XSSFWorkbook workBook;
		try {
			String testDataPath = System.getProperty("user.dir") + "/src/test/resources/testData/TestData.xlsx";
			workBook = new XSSFWorkbook(new FileInputStream(testDataPath));
			XSSFSheet sheet = workBook.getSheet(SheetName);
			for (int j = 0; j <= sheet.getLastRowNum(); j++)
				if (sheet.getRow(j).getCell(1).getStringCellValue().equalsIgnoreCase(TCID)) {
					for (int i = 0; i <= sheet.getRow(0).getLastCellNum(); i++) {
						try {
							objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
									objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
											sheet.getRow(j).getCell(i).getStringCellValue()));

						} catch (IllegalStateException e) {
							try {
								objHashTable.put(sheet.getRow(0).getCell(i).getStringCellValue(),
										"" + sheet.getRow(j).getCell(i).getNumericCellValue());
							} catch (IllegalStateException ex) {
								System.out.println(sheet.getRow(0).getCell(j).getStringCellValue());
							}
						} catch (NullPointerException e) {
							objPojo.getObjHashTable().put("", "");
						}
					}
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Then("Get the Token Number From {string} Response")
	public void get_the_token_number_from_response(String xpath) {
		String token = jsonPathEvaluator.get(xpath);
		objPojo.getObjUtilities().setDataPool("RuntimeGenratedToken", token);
	}

	@Then("I go to {string} and {string} Api Request Saved in Json File {string} with Barear Token")
	public void i_go_to_and_api_request_saved_in_json_file_with_barear_token(String string, String string2,
			String string3) {
		String RuntimeTokenNumber = objPojo.getObjUtilities().dpString("RuntimeGenratedToken");
		String RuntimeorderNumber = objPojo.getObjUtilities().dpString("RuntimeOderID");
		String jsonfilepath = System.getProperty("user.dir") + "/src/test/resources/ApiJsonData//" + string3;
		String file = Utilities.readLineByJava8(jsonfilepath);
		RestAssuredConfig config = RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

		String P  = "POST";
		String P1 = "PATCH";
		String D  = "DELETE";
		
		if (P.equalsIgnoreCase(string2)) {
			System.out.print(BaseUri + string);

			httprequest = RestAssured.given().config(config).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + RuntimeTokenNumber).body(file).log().all();
			response = httprequest.post(BaseUri + string);
		}
		if (P1.equalsIgnoreCase(string2)) {
			System.out.print(BaseUri + string + RuntimeorderNumber);

			httprequest = RestAssured.given().config(config).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + RuntimeTokenNumber).body(file).log().all();
			response = httprequest.patch(BaseUri + string);
		}
		if (D.equalsIgnoreCase(string2)) {
			System.out.print(BaseUri + string + RuntimeorderNumber);

			httprequest = RestAssured.given().config(config).header("Content-Type", "application/json")
					.header("Authorization", "Bearer " + RuntimeTokenNumber).body(file).log().all();
			response = httprequest.delete(BaseUri + string);
		}
		System.out.print(response.getStatusCode());
		System.out.print(response.headers());
		System.out.print("Reponse is : " + response.asString());
		jsonPathEvaluator = response.jsonPath();
	}

	@Then("Get the Order Number From {string} Response")
	public void get_the_order_number_from_response(String xpath) {
		String ordernumber = jsonPathEvaluator.get(xpath);
		objPojo.getObjUtilities().setDataPool("RuntimeOderID", ordernumber);
	}
}
