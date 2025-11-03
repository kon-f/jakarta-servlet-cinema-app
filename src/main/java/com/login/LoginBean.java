package com.login;

public class LoginBean {  //Simple Bean class, recommended convention
	private String userName;
	private String password;
	public String getUserName() {
	return userName;
	}
	public void setUserName(String userName) {
	this.userName = userName;
	}
	public String getPassword() {
	return password;
	}
	public void setPassword(String password) {
	this.password = password;
	}

}
