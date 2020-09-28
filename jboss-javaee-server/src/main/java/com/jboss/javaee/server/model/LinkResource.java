package com.jboss.javaee.server.model;

import java.io.Serializable;
import java.net.URI;

import javax.ws.rs.core.Link;

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
	public LinkResource(Link link) {
		this.rel = link.getRel();
		this.type = link.getType();
		this.uri = link.getUri();
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
}
