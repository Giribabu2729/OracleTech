package com.oracle.tech.exceptions;

public class TechChallengeException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public TechChallengeException(String errorMessage) {
		this.errorMessage = errorMessage;

	}

}
