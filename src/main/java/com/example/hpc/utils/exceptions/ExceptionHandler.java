package com.example.hpc.utils.exceptions;

public class ExceptionHandler extends RuntimeException {
	private String message;
	private int code;

	public ExceptionHandler(String message, int code) {
		super(message);
		this.message = message;
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}
}
