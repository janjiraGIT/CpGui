package com.mobilityguard.acc.data;

import com.mobilityguard.acc.controller.JsonController;
import com.mobilityguard.acc.scripts.ScriptRunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


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
    private static final Logger log = (Logger) LoggerFactory.getLogger(DataTypeInfo.class);

    /**
     * @return JsonObject of Access.
     */
    public JSONObject getData(final String addrUrl) {
        final JsonController jsoncontroller = new JsonController();
        try {
            jsonObj = jsoncontroller.loadFile(addrUrl);
            if (jsonObj.isEmpty()) {
                log.error("Json Object is empty!");
            }
        } catch (IOException e) {
            log.error("IOException" + e.getStackTrace());
        } catch (ParseException e) {
            log.error("ParseException" + e.getStackTrace());
        }
        return jsonObj;
    }

    public String getNetwork() {
        return NETWORK;
    }

    public String getTlss() {
        return TLSS;
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


    public String loadNetworkInfo() {
        final String jsonStr = ScriptRunner.getNetworkInterfaces();
        return jsonStr.toString();
    }
}
