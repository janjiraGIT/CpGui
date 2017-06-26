package com.mobilityguard.acc.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.mobilityguard.acc.json.JsonResponse;

public class JsonController {
	public JSONObject loadJsonData() throws IOException, ParseException{
		final JsonResponse jsonResponse = new JsonResponse();
		final JSONObject jsonObj = jsonResponse.readJsonFile();	
		return jsonObj;		
	}
}
