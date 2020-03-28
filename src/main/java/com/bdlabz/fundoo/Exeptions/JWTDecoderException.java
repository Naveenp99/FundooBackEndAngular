package com.bdlabz.fundoo.Exeptions;

public class JWTDecoderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public JWTDecoderException(String message) {
		super();
		this.message = message;
	}
	
	
	
}
