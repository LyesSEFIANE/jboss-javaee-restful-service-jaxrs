package com.jboss.javaee.client.model;

import java.io.Serializable;
import java.net.URI;

/**
 * 
 * LinkResource.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-08
 *
 */
public class LinkResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private String rel;
	private String type;
	private URI uri;

	public LinkResource() {
		//
	}

	public LinkResource(String rel, String type, URI uri) {
		this.rel = rel;
		this.type = type;
		this.uri = uri;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return uri + "\n\n";
	}

}
