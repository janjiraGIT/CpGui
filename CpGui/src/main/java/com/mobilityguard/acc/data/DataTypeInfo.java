package com.mobilityguard.acc.data;

import java.io.IOException;

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
	public static final String MAINTAIN = "Maintain";
	public static final String ACTIVATEEX = "Activate Exchanges";
	private JSONObject jsonObj = null;

	public JSONObject getStatus() {
    	final JsonController jsoncontroller = new JsonController();
    	try {
			jsonObj = jsoncontroller.loadJsonData();
			if (jsonObj.isEmpty()) {
				System.out.println("Json Object is empty");			
			}
			System.out.println("Testing on console : " + jsonObj);
			String text1 = (String) jsonObj.get("text1");
			System.out.println(text1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObj;
	}
	public String getNetwork() {
		return NETWORK;
	}
	public String getAccess() {
		return ACCESS;
	}
	public String getTLSS() {
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
