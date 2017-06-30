package com.mobilityguard.acc.test;

import java.util.HashMap;

import com.mobilityguard.acc.objects.Network;

public class ConsoleTest {

	public static void main(String[] args) {
		Network nw = new Network();
		nw.setHostName("kdfjlajdfla");
		nw.setDomain("jdfkalfdklas");
		nw.setDns1("aaa");
		nw.setDns2("df");
		HashMap<String,String> hmap = new HashMap<String,String>();
		hmap.put("address", "10.230.34");
		nw.setNetworkInter0Ipv4(hmap);	
		System.out.println(nw);
	}
}
