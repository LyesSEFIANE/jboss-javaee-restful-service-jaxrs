package com.jboss.javaee.client.data;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.jboss.javaee.client.model.Book;
import com.jboss.javaee.client.service.BookService;

@RequestScoped
public class BookListProducer {

	@Inject
	private BookService bookService;

	private List<Book> books;

	@Produces
	@Named
	public List<Book> getbooks() {
		return books;
	}

	@PostConstruct
	public void retieveBooks() {
		books = bookService.getBooks();
	}

	public void onBookListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Book book) {
		retieveBooks();
	}

}
