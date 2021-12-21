package com.lawencon.assetsmanagement.exception;

public class ValidationIamException extends RuntimeException {
	
	private static final long serialVersionUID = 5753945846050468661L;

	public ValidationIamException(Throwable cause) {
		super(cause);
	}

	public ValidationIamException(String message) {
		super(message);
	}
}
