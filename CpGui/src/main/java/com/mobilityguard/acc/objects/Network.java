package com.mobilityguard.acc.objects;

import java.io.Serializable;
import java.util.HashMap;

public class Network implements Serializable{
	private static final long serialVersionUID =1L;
	private String hostName;
	private String domain;
	private String dns1;
	private String dns2;
	// HashMap<String,String> hmap = new HashMap<String,String>();
	private HashMap<String,String> networkInter0Ipv4;
	private HashMap<String,String> networkInter0Ipv6;
	private HashMap<String,String> networkInter1Ipv4;
	private HashMap<String,String> networkInter1Ipv6;
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDns1() {
		return dns1;
	}
	public void setDns1(String dns1) {
		this.dns1 = dns1;
	}
	public String getDns2() {
		return dns2;
	}
	public void setDns2(String dns2) {
		this.dns2 = dns2;
	}
	public HashMap<String, String> getNetworkInter0Ipv4() {
		return networkInter0Ipv4;
	}
	public void setNetworkInter0Ipv4(HashMap<String, String> networkInter0Ipv4) {
		this.networkInter0Ipv4 = networkInter0Ipv4;
	}
	public HashMap<String, String> getNetworkInter0Ipv6() {
		return networkInter0Ipv6;
	}
	public void setNetworkInter0Ipv6(HashMap<String, String> networkInter0Ipv6) {
		this.networkInter0Ipv6 = networkInter0Ipv6;
	}
	public HashMap<String, String> getNetworkInter1Ipv4() {
		return networkInter1Ipv4;
	}
	public void setNetworkInter1Ipv4(HashMap<String, String> networkInter1Ipv4) {
		this.networkInter1Ipv4 = networkInter1Ipv4;
	}
	public HashMap<String, String> getNetworkInter1Ipv6() {
		return networkInter1Ipv6;
	}
	public void setNetworkInter1Ipv6(HashMap<String, String> networkInter1Ipv6) {
		this.networkInter1Ipv6 = networkInter1Ipv6;
	}
	@Override
	public String toString() {
		return "Network [hostName=" + hostName + ", domain=" + domain + ", dns1=" + dns1 + ", dns2=" + dns2
				+ ", networkInter0Ipv4=" + networkInter0Ipv4 + ", networkInter0Ipv6=" + networkInter0Ipv6
				+ ", networkInter1Ipv4=" + networkInter1Ipv4 + ", networkInter1Ipv6=" + networkInter1Ipv6 + "]";
	}
	
	
	

}
