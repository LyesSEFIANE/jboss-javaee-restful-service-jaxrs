package com.jboss.javaee.server.exception.entity;

import java.util.Arrays;

/**
 * 
 * ErrorMessage.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-09
 *
 */
public class ErrorMessage {

	private String errorMessageValue;
	private String errorMessageKey;

	public ErrorMessage() {

	}

	public ErrorMessage(String errorMessageValue) {
		this.errorMessageValue = errorMessageValue;
		this.errorMessageKey = Arrays.stream(ErrorMessages.values())
		          .filter(bl -> bl.getErrorMessage().equalsIgnoreCase(errorMessageValue))
		          .findFirst()
		          .orElse(ErrorMessages.ERROR_MESSAGE)
		          .name();

	}
	
	public ErrorMessage(String errorMessageValue, String errorMessageKey) {
		this.errorMessageValue = errorMessageValue;
		this.errorMessageKey = errorMessageKey;
	}

	public String getErrorMessageValue() {
		return errorMessageValue;
	}

	public void setErrorMessageValue(String errorMessageValue) {
		this.errorMessageValue = errorMessageValue;
	}

	public String getErrorMessageKey() {
		return errorMessageKey;
	}

	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}
}
