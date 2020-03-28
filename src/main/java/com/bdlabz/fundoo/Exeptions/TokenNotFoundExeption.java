package com.bdlabz.fundoo.Exeptions;

public class TokenNotFoundExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public TokenNotFoundExeption(String message) {
		super();
		this.message = message;
	}

	
	
}
