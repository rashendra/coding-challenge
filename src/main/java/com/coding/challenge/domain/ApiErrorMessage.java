package com.coding.challenge.domain;

public class ApiErrorMessage {

	private String message;

	private String errorCode;

	public ApiErrorMessage(String message, String errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
