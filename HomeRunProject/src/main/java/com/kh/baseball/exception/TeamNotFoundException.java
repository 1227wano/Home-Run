package com.kh.baseball.exception;

public class TeamNotFoundException extends RuntimeException {

	public TeamNotFoundException(String message) {
		super(message);
	}
}
