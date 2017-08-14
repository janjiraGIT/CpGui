package com.mobilityguard.acc.object;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
//  Map<String,Object> userData = new HashMap<String,Object>();
//  Map<String,String> nameStruct = new HashMap<String,String>();
//  nameStruct.put("first", "jjdsklfajdfö");
//  nameStruct.put("last", "Sixpack");
//  userData.put("name", nameStruct);
//  userData.put("gender", "MALE");
//  userData.put("verified", Boolean.FALSE);
//  userData.put("userImage", "Rm9vYmFyIQ==");
//  ObjectMapper mapper = new ObjectMapper();
//  User user = mapper.readValue((new File("/home/janjira/code/workspace/acc/cpgui/jsonFile/user.json")), User.class);
//  String fname = user.getName().getFirst().toString();
//  System.out.println(fname);

  // case read from the file.
//  final ObjectMapper mapper = new ObjectMapper();
//  NetworkInterface nif = mapper.readValue((new File("/home/janjira/code/workspace/acc/cpgui/jsonFile/ipv.json")), NetworkInterface.class);
//  String address = nif.getIpv().getAddress().toString();
//  System.out.println(address);
//  System.out.println(nif.getIpv().getDhcp());
//
//  // case write to the file.
//  Map<String,Object> ipvMap = new HashMap<String,Object>();
//  ipvMap.put("address", "dkfjlaksfkdksfjalkfjölajflödajföjö");
//  ipvMap.put("dhcp", Boolean.TRUE);
//  ipvMap.put("netmask", "uij");
//  ipvMap.put("gateway", "kss");
//
//  Map<String,Object> mapObj4 = new HashMap<String,Object>();
//  mapObj4.put("ipv4", ipvMap);
//  mapper.writeValue((new File("/home/janjira/code/workspace/acc/cpgui/jsonFile/ipv-new.json")), mapObj4);
//

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
//	  Map<String,Object> userData = new HashMap<String,Object>();
//	  Map<String,String> nameStruct = new HashMap<String,String>();
//	  nameStruct.put("first", "jjdsklfajdfö");
//	  nameStruct.put("last", "Sixpack");
//	  userData.put("name", nameStruct);
//	  userData.put("gender", "MALE");
//	  userData.put("verified", Boolean.FALSE);
//	  userData.put("userImage", "Rm9vYmFyIQ==");
//	  ObjectMapper mapper = new ObjectMapper();
//	  User user = (User) mapper.readValue((new File("/home/janjira/code/workspace/acc/cpgui/jsonFile/user.json")), User.class);
//	  String fname = user.getName().getFirst().toString();
//	  System.out.println(fname);
		
		
		
		// example write json 
	  Map<String,Object> ipvMap = new HashMap<String,Object>();
	  ipvMap.put("address", "dkfjlaksfkdksfjalkfjölajflödajföjö");
	  ipvMap.put("dhcp", Boolean.TRUE);
	  ipvMap.put("netmask", "uij");
	  ipvMap.put("gateway", "kss");
	  
	  Map<String,Object> mapObj4 = new HashMap<String,Object>();
	  mapObj4.put("ipv4", ipvMap);
	  mapObj4.put("ipv6", ipvMap);
	  
	  Map<String,Object> eth0 = new HashMap<String,Object>();
	  eth0.put("eth0", mapObj4);
	  eth0.put("eth1", mapObj4);
	  
	  final ObjectMapper mapper = new ObjectMapper();
	  mapper.writeValue((new File("/Users/janjiraeriksson/code/git/CpGui/CpGui/jsonFile/ipv-create.json")), eth0);
//	
//	  //case read from the file.
//	  final ObjectMapper mapperRead = new ObjectMapper();
//	  Eth0 ipvInfo= mapperRead.readValue((new File("/Users/janjiraeriksson/code/git/CpGui/CpGui/jsonFile/ipv1.json")), Eth0.class);
//	  String address = ipvInfo.getIpvInfo().getAddress().toString();
//	  System.out.println(address);

		
		// use ObjectMapper tree can read 
//		final ObjectMapper mapper= new ObjectMapper();
//		JsonNode rootNode = mapper.readTree(new File("/Users/janjiraeriksson/code/git/CpGui/CpGui/jsonFile/ipv.json"));
//		
//		JsonNode eth0 = rootNode.path("eth0");
//		JsonNode ipvNode = eth0.path("ipv4");
//		JsonNode addNode = ipvNode.path("address");
//		JsonNode dhcpNode = ipvNode.path("dhcp");
//		System.out.println(addNode.asText() );
//		System.out.println(dhcpNode.asBoolean() );
//		
//		JsonNode eth1 = rootNode.path("eth1");
//		JsonNode ipvNode1 = eth1.path("ipv4");
//		JsonNode addNode1 = ipvNode1.path("address");
//		JsonNode dhcpNode1 = ipvNode1.path("dhcp");
//		System.out.println(addNode1.asText() );
//		System.out.println(dhcpNode1.asBoolean() );
//		
	}

}
