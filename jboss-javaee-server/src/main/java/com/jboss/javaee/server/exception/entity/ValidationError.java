package com.jboss.javaee.server.exception.entity;

/**
 * 
 * ValidationError.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-09
 *
 */
public class ValidationError {

	private String property;
	private String message;


	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ValidationError [property=" + property + ", message=" + message + "]";
	}
}
