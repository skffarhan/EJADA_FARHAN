package com.pageFactory.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.generics.Pojo;

public class jsonPage {
	private Pojo objPojo;

	public jsonPage(Pojo pojo) {
		objPojo = pojo;
	}

	@SuppressWarnings("resource")
	public void readrequiresdataromjsoninputfile(String Randomemailid) throws IOException {
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\PostTestData.json";

		File filetobemodified = new File(filepath);
		String oldcontent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("clientEmail")) {
				String[] strAr = strCurrentLine.split("clientEmail");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(filetobemodified));
			String line = reader.readLine();
			while(line != null) {
				oldcontent =oldcontent +line +System.lineSeparator();
				line=reader.readLine();
			}
			String newContent = oldcontent.replaceAll(abc, Randomemailid + "\"");
			writer = new FileWriter(filetobemodified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}
	@SuppressWarnings("resource")
	public void readrequiresdataromjsoninputfileforrandomname(String RandomName) throws IOException {
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\CreateOrderidTestData.json";

		File filetobemodified = new File(filepath);
		String oldcontent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("customerName")) {
				String[] strAr = strCurrentLine.split("customerName");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(filetobemodified));
			String line = reader.readLine();
			while(line != null) {
				oldcontent =oldcontent +line +System.lineSeparator();
				line=reader.readLine();
			}
			String newContent = oldcontent.replaceAll(abc, RandomName + "\"");
			writer = new FileWriter(filetobemodified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

}