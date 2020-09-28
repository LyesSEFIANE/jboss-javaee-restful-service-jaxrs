package com.jboss.javaee.client.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.jboss.javaee.client.model.Book;
import com.jboss.javaee.client.service.BookService;

@Model
public class BookController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private BookService bookService;

	@Inject
	private FacesContext facesContext;

	@Produces
	@Named
	private Book book;

	@PostConstruct
	public void initNewBook() {
		book = new Book();
	}

	public void register() {
		try {
			bookService.save(book);
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
			facesContext.addMessage(null, m);
			initNewBook();
		} catch (RuntimeException e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Registration unsuccessful");
			facesContext.addMessage(null, m);
		}
	}
}
