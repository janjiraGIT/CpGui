package com.mobilityguard.acc.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mobilityguard.acc.data.DataTypeInfo;

public class ConsoleTest {
    static final Logger logger = Logger.getLogger(ConsoleTest.class);
    private static JSONObject jsonObj = null;


	public static void main(String[] args) {
		//checkJsonObj();
		buildJson();
	}


	/**
	 * 
	 *///
	private static void checkJsonObj() {
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
					String searchKey = "janjira"; // userTf
					//itemMap.containsKey(searchKey);
					//itemMap.get(searchKey);
					if (itemMap.containsKey(searchKey)) {
						String password = itemMap.get(searchKey);
						
						System.out.println("Found it!  " + "user : " + searchKey + "password : " + password );
			}
		}
	}
	@SuppressWarnings("unchecked")
	private static void buildJson() {
	
		JSONObject ca = new JSONObject();	
		JSONObject IpObj = new JSONObject();
		ca.put("Control Panel Access", IpObj);
		JSONArray ipArray = new JSONArray();
		JSONObject ip1 = new JSONObject();		
		ip1.put("ip1","192.168.1.1");
		JSONObject ip2 = new JSONObject();
		ip2.put("ip2","192.168.1.2");
		JSONObject ip3 = new JSONObject();
		ip3.put("ip3","192.168.1.3");
		ipArray.add(ip1);
		ipArray.add(ip2);
		ipArray.add(ip3);
		IpObj.put("Ip", ipArray);

		JSONArray adminArray = new JSONArray();
		JSONObject user = new JSONObject();
		JSONObject pass = new JSONObject();
		user.put("userid", "Anders");
		user.put("password", "Losen123");
		adminArray.add(user);
		adminArray.add(pass);
		ca.put("Admin", adminArray);
		System.out.println(ca);
		
	}
}
