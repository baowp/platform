package com.abbcc.exception;

public class DataAccessException extends Exception {

	public DataAccessException() {

		super();
	}

	public DataAccessException(String msg) {
		super("DataAccessException: " + msg);
	}

	public DataAccessException(String msg, Throwable cause) {
		super("DataAccessException: " + msg, cause);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
