package com.bdlabz.fundoo.util;


public class Response {

	 public Response(String name, int messagecode) {
		super();
		this.name = name;
		this.messagecode = messagecode;
	}

	String name; 
	 int messagecode;
	 Object token;
	
	public Response() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMessagecode() {
		return messagecode;
	}

	public void setMessagecode(int messagecode) {
		this.messagecode = messagecode;
	}

	public Object getToken() {
		return token;
	}

	public void setToken(Object token) {
		this.token = token;
	}

	public Response(String name, int messagecode, Object token) {
		super();
		this.name = name;
		this.messagecode = messagecode;
		this.token = token;
	}
	
}
