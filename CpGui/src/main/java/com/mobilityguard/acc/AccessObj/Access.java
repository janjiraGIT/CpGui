package com.mobilityguard.acc.AccessObj;

import java.util.ArrayList;
import java.util.List;

public class Access {
	String ca;
	List<Admin> listAdmin ;
	List<Ip> listIp;

	public String getCa() {
		return ca;
	}

	public void setCa(String ca) {
		this.ca = ca;
	}
	
	@Override
	public String toString() {
		return ca + " : " + listAdmin + listIp ;
	}

	public List<Admin> getAdmin(){
		listAdmin = new ArrayList<Admin>();
		Admin admin1 = new Admin("Benjamin");
		Admin admin2 = new Admin("Joskim");	
		listAdmin.add(admin1);
		listAdmin.add(admin2);
		return listAdmin;
		
	}
	
	public List<Ip> getIP(){
		listIp = new ArrayList<Ip>();
		Ip ip1 = new Ip();
		Ip ip2 = new Ip();
		ip1.setIp("123.456.789.34");
		ip2.setIp("123.456.789.35");
		listIp.add(ip1);
		listIp.add(ip2);
		return listIp;
		
	}

	

}
