package com.yh.model;

public class Account {
	private String username;
	private String password;
	private int rid;
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(String username,String password,int rid){
		this.username=username;
		this.password=password;
		this.rid=rid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	
}
