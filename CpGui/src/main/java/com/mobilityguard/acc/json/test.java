package com.mobilityguard.acc.json;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import com.mobilityguard.acc.controller.JsonController;

public class test {
	public static void main(String[] args) {
    	JsonController jsoncontroller = new JsonController();
    	try {
			final JSONObject jsonObj = jsoncontroller.loadJsonData();
			if (jsonObj.isEmpty()) {
				System.out.println("Json Object is empty");			
			}
			System.out.println("Testing on console : " + jsonObj);
			String text1 = (String) jsonObj.get("text1");
			System.out.println(text1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
