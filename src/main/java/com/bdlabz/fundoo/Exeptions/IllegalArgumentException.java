package com.bdlabz.fundoo.Exeptions;

public class IllegalArgumentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public IllegalArgumentException(String message) {
		super();
		this.message = message;
	}
	
	
}
