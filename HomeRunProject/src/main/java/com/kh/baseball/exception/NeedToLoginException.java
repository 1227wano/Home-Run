package com.kh.baseball.exception;

public class NeedToLoginException extends RuntimeException{
	public NeedToLoginException(String message) {
		super(message);
	}
}
