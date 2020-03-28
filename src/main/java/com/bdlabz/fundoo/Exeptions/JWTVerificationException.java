package com.bdlabz.fundoo.Exeptions;

public class JWTVerificationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public JWTVerificationException(String message) {
		super();
		this.message = message;
	}
	
	
}
