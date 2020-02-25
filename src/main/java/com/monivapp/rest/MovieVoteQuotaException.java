package com.monivapp.rest;

public class MovieVoteQuotaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieVoteQuotaException() {
	}

	public MovieVoteQuotaException(String arg0) {
		super(arg0);
	}

	public MovieVoteQuotaException(Throwable arg0) {
		super(arg0);
	}

	public MovieVoteQuotaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MovieVoteQuotaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}