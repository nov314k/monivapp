package com.monivapp.rest;

public class MovieAddException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieAddException() {
	}

	public MovieAddException(String arg0) {
		super(arg0);
	}

	public MovieAddException(Throwable arg0) {
		super(arg0);
	}

	public MovieAddException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MovieAddException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}