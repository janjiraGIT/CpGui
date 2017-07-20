package com.mobilityguard.acc.controller;

import com.mobilityguard.acc.json.JsonResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class JsonController {
	private static final String ACCESS = "./jsonFile/access.json";
	private static final String IP = "./jsonFile/ip.json";
	private static final String SYSLOG = "./jsonFile/syslog.json";
	private static final String ADMIN = "./jsonFile/admin.json";

    /**
     * load info av Access.
     */
    public JSONObject loadJsonAccess() throws IOException, ParseException {
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
    /**
     * load info av Syslog.
     */
    public JSONObject loadJsonSyslog() throws IOException, ParseException {
        final JsonResponse jsonResponse = new JsonResponse();
        final JSONObject jsonObj = jsonResponse.readJsonFile(SYSLOG);
        return jsonObj;
    }
    /**
     * load info av Syslog.
     */
    public JSONObject loadIpInof() throws IOException, ParseException {
        final JsonResponse jsonResponse = new JsonResponse();
        final JSONObject jsonObj = jsonResponse.readJsonFile(IP);
        return jsonObj;
    }
}
