package com.dfweb.ex;


public class InfoTipException extends Exception {

	private String message;

	public InfoTipException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
