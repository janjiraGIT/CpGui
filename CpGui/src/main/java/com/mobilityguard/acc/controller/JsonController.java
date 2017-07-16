package com.mobilityguard.acc.controller;

import com.mobilityguard.acc.json.JsonResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class JsonController {
	private static final String ACCESS = "./jsonFile/access.json";
	private static final String ADMIN = "./jsonFile/admin.json";
    /**
     * load info av Access.
     */
    public JSONObject loadJsonData() throws IOException, ParseException {
        final JsonResponse jsonResponse = new JsonResponse();
        final JSONObject jsonObj = jsonResponse.readJsonFile(ACCESS);
        return jsonObj;
    }
    /**
     * load info av Admin users.
     */
    public JSONObject loadJsonAdmin() throws IOException, ParseException {
        final JsonResponse jsonResponse = new JsonResponse();
        final JSONObject jsonObj = jsonResponse.readJsonFile(ADMIN);
        
        return jsonObj;
    }
}
