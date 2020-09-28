package com.jboss.javaee.server.exception;

/**
 * 
 * BookException.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-07
 *
 */
public abstract class BookException extends Exception {

	private static final long serialVersionUID = 1L;


	public BookException(String message) {
		super(message);
	}
}
