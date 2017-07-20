package com.mobilityguard.acc.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonResponse {
	private static Reader reader = null;
	private static JSONObject jsonObject = null;
	final static Logger logger = Logger.getLogger(JsonResponse.class);
	private static FileWriter file = null;

	public JSONObject readJsonFile(final String URL) throws IOException, ParseException{
		try {
			reader = new FileReader(URL);
		}catch(IOException e){
			logger.error("Cound not find the Json file! Please check the Json file again");
		}
		final JSONParser jsonParser = new JSONParser();
		jsonObject = (JSONObject) jsonParser.parse(reader);	
		return jsonObject;
	}	
	
	public JSONObject writeJsonFile(final String FILE_ADDRESS, final JSONObject obj){
		try {
			file = new FileWriter(FILE_ADDRESS);
			file.write(obj.toString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
			return obj;	
	}
}	

