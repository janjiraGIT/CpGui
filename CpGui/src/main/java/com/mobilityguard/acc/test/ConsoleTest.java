package com.mobilityguard.acc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mobilityguard.acc.data.DataTypeInfo;
import com.mobilityguard.acc.objects.Network;

public class ConsoleTest {
    static final Logger logger = Logger.getLogger(ConsoleTest.class);
    private static JSONObject jsonObj = null;


	public static void main(String[] args) {
		//https://www.mkyong.com/java/json-simple-example-read-and-write-json/
		DataTypeInfo data = new DataTypeInfo();
		jsonObj = data.getAdmins();
		//System.out.println(jsonObj);
		final JSONArray adminObj = (JSONArray) jsonObj.get("Admin");
		for (int i=0;i<adminObj.size();i++) {
			JSONObject obj = (JSONObject) adminObj.get(i);
			String objUser = (String) obj.get("userid");
			String objPassword = (String) obj.get("password");
				Map<String, String> itemMap = new HashMap<String,String>();
					itemMap.put(objUser,objPassword);
					System.out.println(itemMap);
		}

	}
}
