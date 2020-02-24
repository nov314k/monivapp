package com.monivapp.rest;

public class MovieAddingQuotaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieAddingQuotaException() {
	}

	public MovieAddingQuotaException(String arg0) {
		super(arg0);
	}

	public MovieAddingQuotaException(Throwable arg0) {
		super(arg0);
	}

	public MovieAddingQuotaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MovieAddingQuotaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}