package com.nt.exception;

public class GuestServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4088567450708309117L;

	public GuestServiceException(String msg) {
		super(msg);
		System.out.println("exception");

	}

}
