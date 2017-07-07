package com.mobilityguard.acc.controller;

import com.mobilityguard.acc.json.JsonResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class JsonController {
    /**
     * load Json data.
     */
    public JSONObject loadJsonData() throws IOException, ParseException {
        final JsonResponse jsonResponse = new JsonResponse();
        final JSONObject jsonObj = jsonResponse.readJsonFile();
        return jsonObj;
    }
}
