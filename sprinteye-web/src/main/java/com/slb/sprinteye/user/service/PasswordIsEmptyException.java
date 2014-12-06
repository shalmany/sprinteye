package com.slb.sprinteye.user.service;

public class PasswordIsEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1530358185734675787L;
	
	public PasswordIsEmptyException(String message) {
        super(message);
    }


}
