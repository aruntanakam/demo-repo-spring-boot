package com.arun.model;

public class UserInfo {
	
	private String name;
	private String userName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public UserInfo(String name, String username) {
		super();
		this.name = name;
		this.userName = username;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
	

}
