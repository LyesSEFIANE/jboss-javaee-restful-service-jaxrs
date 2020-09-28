package com.jboss.javaee.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Hypermedia.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-08
 *
 */
public class Hypermedia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<LinkResource> links = new ArrayList<>();

	public void addLink(LinkResource link) {
		links.add(link);
	}

	public List<LinkResource> getLinks() {
		return links;
	}

	public void setLinks(List<LinkResource> links) {
		this.links = links;
	}

}
