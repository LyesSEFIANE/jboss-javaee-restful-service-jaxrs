package com.jboss.javaee.server.exception.entity;

/**
 * 
 * ErrorMessages.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-09
 *
 */
public enum ErrorMessages {

	COULD_NOT_CREATE_BOOK("Could not create book"), 
	COULD_NOT_UPDATE_BOOK("Could not update book"),
	COULD_NOT_DELETE_BOOK("Could not delete book"), 
	NO_BOOK_FOUND("No book found for provided id"),
	BOOK_ALREADY_EXISTS("Book already exists"),
	ERROR_MESSAGE("Error Message");

	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
