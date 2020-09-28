package com.jboss.javaee.server.rest;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.NotBlank;

import com.jboss.javaee.server.exception.BookAlreadyExistsException;
import com.jboss.javaee.server.exception.BookNotFoundException;
import com.jboss.javaee.server.model.Book;
import com.jboss.javaee.server.model.LinkResource;
import com.jboss.javaee.server.service.BookService;

/**
 * 
 * BookResource.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-06
 *
 */
@Stateless
@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

	@Inject
	private BookService bookService;

	@Context
	private UriInfo uriInfo;

	@GET
	public Response getAllBooks() {
		List<Book> books = bookService.getAllBooks();
		books.stream().forEach(book -> {
			LinkResource self = buildLink(book, "getBookByIsbn", "self", "GET");
			LinkResource remove = buildLink(book, "delete", "remove", "DELETE");
			book.addLink(self);
			book.addLink(remove);
		});
		return Response.status(Status.OK).entity(books).build();
	}

	@GET
	@Path("{isbn:[0-9][0-9]*}")
	public Response getBookByIsbn(final @PathParam("isbn") @NotBlank String isbn) throws BookNotFoundException {

		Book book = bookService//
				.findBookByIsbn(isbn)//
				.orElseThrow(() -> new BookNotFoundException("Book " + isbn + " Not Found"));

		LinkResource self = buildLink(book, "getBookByIsbn", "self", "GET");
		book.addLink(self);

		return Response.status(Status.OK).entity(book).build();
	}

	@POST
	public Response save(final @Valid Book book) throws BookAlreadyExistsException {
		Book savedBook = null;
		Optional<Book> optionalBook = bookService.save(book);
		if(optionalBook.isPresent()) {
			savedBook = optionalBook.get();
			LinkResource self = buildLink(book, "getBookByIsbn", "self", "GET");
			LinkResource remove = buildLink(book, "delete", "remove", "DELETE");
			book.addLink(self);
			book.addLink(remove);
		}
		return Response.status(Status.OK).entity(savedBook).build();
	}

	@DELETE
	@Path("{isbn:[0-9][0-9]*}")
	public Response delete(final @PathParam("isbn") String isbn) {
		return Response.status(Status.OK).build();
	}

	/**
	 * Build Link
	 * 
	 * @param book
	 * @param method
	 * @param rel
	 * @param type
	 * @return LinkResource
	 */
	private LinkResource buildLink(Book book, String method, String rel, String type) {
		Link link = Link//
				.fromUri(uriInfo//
						.getBaseUriBuilder()//
						.path(getClass())//
						.path(getClass(), method)//
						.build(book.getIsbn()))//
				.rel(rel)//
				.type(type)//
				.build();

		return new LinkResource(link);
	}

}
