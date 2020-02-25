package com.monivapp.rest;

public class MovieVotingQuotaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieVotingQuotaException() {
	}

	public MovieVotingQuotaException(String arg0) {
		super(arg0);
	}

	public MovieVotingQuotaException(Throwable arg0) {
		super(arg0);
	}

	public MovieVotingQuotaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MovieVotingQuotaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
}