package com.monivapp.rest;

public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotFoundException() {
	}

	public MovieNotFoundException(String arg0) {
		super(arg0);
	}

	public MovieNotFoundException(Throwable arg0) {
		super(arg0);
	}

	public MovieNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MovieNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}