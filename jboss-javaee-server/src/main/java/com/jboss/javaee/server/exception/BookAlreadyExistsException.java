package com.jboss.javaee.server.exception;

/**
 * 
 * BookAlreadyExistsException.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-06
 *
 */
public class BookAlreadyExistsException extends BookException {

	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException(String message) {
		super(message);
	}

}
