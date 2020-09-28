package com.jboss.javaee.server.exception;

/**
 * 
 * BookNotFoundException.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-07
 *
 */
public class BookNotFoundException extends BookException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String message) {
		super(message);
	}
}
