package com.mobilityguard.acc.data;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.mobilityguard.acc.controller.JsonController;

public class DataTypeInfo {
    public static final String STATUS = "Status";
    public static final String NETWORK = "Network";
    public static final String ACCESS = "Access Config for Control Panel";
    public static final String TLSS = "TLS Server settings";
    public static final String SYSLOG = "Syslog";
    public static final String REPORTCONFIG = "Report Config";
    public static final String MAINTAIN = "Maintenance";
    public static final String ACTIVATEEX = "Activate Changes";
    private JSONObject jsonObj = null;
    static final Logger logger = Logger.getLogger(DataTypeInfo.class);

    /**
     * @return JsonObject.
     */
    public JSONObject getStatus() {
        final JsonController jsoncontroller = new JsonController();
        // only for test load Json data to Textfields.
        try {
            jsonObj = jsoncontroller.loadJsonData();
            if (jsonObj.isEmpty()) {
                logger.info("Json Object is empty!");
            }
            logger.info("This is for test on console :" + jsonObj);
            String text1 = (String) jsonObj.get("text1");
            logger.info("Json Object output : " + text1);
        } catch (IOException e) {
            logger.error("IOException" + e.getStackTrace());
        } catch (ParseException e) {
            logger.error("ParseException" + e.getStackTrace());
        }
        return jsonObj;
    }

    public String getNetwork() {
        return NETWORK;
    }

//    public String getAccess() {
//        return ACCESS;
//    }
    /**
     * @return JsonObject.
     */
    public JSONObject getAccess() {
        final JsonController jsoncontroller = new JsonController();
        // only for test load Json data to Textfields.
        try {
            jsonObj = jsoncontroller.loadJsonData();
            if (jsonObj.isEmpty()) {
                logger.info("Json Object is empty!");
            }
        } catch (IOException e) {
            logger.error("IOException" + e.getStackTrace());
        } catch (ParseException e) {
            logger.error("ParseException" + e.getStackTrace());
        }
        return jsonObj;
    }

    public String getTlss() {
        return TLSS;
    }

    public String getSyslog() {
        return SYSLOG;
    }

    public String getReportConfig() {
        return REPORTCONFIG;
    }

    public String getMaintain() {
        return MAINTAIN;
    }

    public String getActiveEx() {
        return ACTIVATEEX;
    }
}
