package com.jboss.javaee.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Book.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-09
 *
 */
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String isbn;

	private String title;

	private List<LinkResource> links = new ArrayList<>();
	
	public Book() {
		// 
	}

	public Book(String id, String isbn, String title, List<LinkResource> links) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.links = links;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<LinkResource> getLinks() {
		return links;
	}

	public void setLinks(List<LinkResource> links) {
		this.links = links;
	}

	public static class BookBuilder {
		private String id;
		private String isbn;
		private String title;
		private List<LinkResource> links;

		public BookBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public BookBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		public BookBuilder setIsbn(String isbn) {
			this.isbn = isbn;
			return this;
		}

		public BookBuilder setHyperlinks(List<LinkResource> links) {
			this.links = links;
			return this;
		}

		public Book createBook() {
			return new Book(id, title, isbn, links);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", links=" + links + "]";
	}
}
