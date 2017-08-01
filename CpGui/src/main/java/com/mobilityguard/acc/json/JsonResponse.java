package com.mobilityguard.acc.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;


public class JsonResponse {
    private static Reader reader = null;
    private static JSONObject jsonObject = null;
    //private static final Logger log = LoggerFactory.getLogger(JsonResponse.class);
    private static FileWriter file = null;

    /**
     *read json file and retrun to jsonobject.
     */
    public JSONObject readJsonFile(final String url) throws IOException, ParseException {
        try {
            reader = new FileReader(url);
        } catch (IOException e) {
            //log.error("Cound not find the Json file! Please check the Json file again");
        }
        final JSONParser jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse(reader);
        return jsonObject;
    }

    /**
     *write json intot file.
     */
    public JSONObject writeJsonFile(final String fileAddress, final JSONObject obj) {
        try {
            file = new FileWriter(fileAddress);
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
