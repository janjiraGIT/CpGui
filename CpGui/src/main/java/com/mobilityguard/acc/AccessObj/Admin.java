package com.mobilityguard.acc.AccessObj;

public class Admin {
	private String user;
	

	public Admin(String user) {
		super();
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return user;
	}

}
