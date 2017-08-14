package com.mobilityguard.acc.json;

import com.mobilityguard.acc.controller.JsonController;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;


public class JsonResponse {
    private static Reader reader = null;
    private static FileWriter file = null;
    private static final Logger log = LoggerFactory.getLogger(JsonController.class);


    /**
     * read json object and update into file.
     */
    public JSONObject readJsonFile(final String url) throws IOException, ParseException {
        try {
            reader = new FileReader(url);
        } catch (IOException e) {
            log.error("Cound not find the Json file! Please check the Json file again");
        }
        final JSONParser jsonParser = new JSONParser();
        if (reader == null) {
            return new JSONObject();
        } else {
            return (JSONObject) jsonParser.parse(reader);
        }
    }

    /**
     * write json object into file.
     */
    public JSONObject writeJsonFile(final String fileAddress, final JSONObject obj) {
        try {
            file = new FileWriter(fileAddress);
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("File not found : " + fileAddress);
        }
        return obj;
    }
}
