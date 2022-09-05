package com.tranction.management.service;

public class InActiveAccountException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InActiveAccountException(String errorMessage) {
		super(errorMessage);
	}

}
