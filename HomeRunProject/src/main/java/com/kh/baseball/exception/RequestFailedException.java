package com.kh.baseball.exception;

public class RequestFailedException extends RuntimeException {
	
	public RequestFailedException(String message) {
		super(message);
	}
	
}
