package com.slb.sprinteye.user.service;


public class DuplicateRoleException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3331611794980305378L;

	public DuplicateRoleException(String message) {
        super(message);
    }
}
