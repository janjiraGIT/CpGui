package com.mobilityguard.acc.json;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonResponse {
	public static final String URL = "./jsonFile/status.json";
	private static Reader reader = null;
	private static JSONObject jsonObject = null;
	public JSONObject readJsonFile() throws IOException, ParseException{
		try {
			reader = new FileReader(URL);
		}catch(IOException e){
			System.out.println("Could not find the file! Please check the Json file again");
		}
		final JSONParser jsonParser = new JSONParser();
		jsonObject = (JSONObject) jsonParser.parse(reader);	
		return jsonObject;
	}
}
