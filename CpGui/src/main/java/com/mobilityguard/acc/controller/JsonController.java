package com.mobilityguard.acc.controller;

import com.mobilityguard.acc.json.JsonResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;



public class JsonController {
    private static final Logger log = LoggerFactory.getLogger(JsonController.class);

    /**
     * load info av Access.
     */
    public JSONObject loadFile(final String urlAddress) throws IOException, ParseException {
        final JsonResponse jsonResponse = new JsonResponse();
        final JSONObject jsonObj = jsonResponse.readJsonFile(urlAddress);
        return jsonObj;
    }

    /**
     * write json data into file.
     */
    public JSONObject writeIntoFile(final String urlAddress, final JSONObject obj ) throws IOException, ParseException {
        final JsonResponse jsonResponse = new JsonResponse();
        final JSONObject jsonObj = jsonResponse.writeJsonFile(urlAddress, obj);
        return jsonObj;
    }
}
