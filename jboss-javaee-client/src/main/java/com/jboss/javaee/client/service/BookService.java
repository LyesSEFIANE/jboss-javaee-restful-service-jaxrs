package com.jboss.javaee.client.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.jboss.javaee.client.model.Book;
import com.jboss.javaee.client.model.LinkResource;

@Stateless
public class BookService {

	private static final String API_URL = "http://localhost:8080/jboss-javaee-server";

	private static final String BOOKS_ENDPOINT = API_URL + "/api/books";

	private Client client;

	private List<Book> cachedBooks;

	@Inject
	private Event<Book> bookEvent;

	public List<Book> getCachedBooks() {
		return cachedBooks;
	}

	@PostConstruct
	private void initNewClient() {
		client = ClientBuilder.newClient();
	}

	@PreDestroy
	public void destroy() {
		client.close();
	}

	public List<Book> getBooks() {
		cachedBooks = new ArrayList<>();
		Response response = client.target(BOOKS_ENDPOINT).request(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + "00NptcPpVWelmeRJ8D6wuQ8BbpRV-bkK3zEcsZOfHT").get();
		JsonArray jsonArray = response.readEntity(JsonArray.class);
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject bookJson = jsonArray.getJsonObject(i);
			List<LinkResource> hypermedia = extractLinks(bookJson.getJsonArray("links"));
			Book book = new Book.BookBuilder()
					.setId(String.valueOf(bookJson.getInt("id")))
					.setIsbn(bookJson.getString("isbn"))
					.setTitle(bookJson.getString("title"))
					.setHyperlinks(hypermedia)
					.createBook();
			
			cachedBooks.add(book);		
		}

		return Collections.unmodifiableList(cachedBooks);
	}

	public Book save(final Book book) {
		Response response = client.target(BOOKS_ENDPOINT).request(MediaType.APPLICATION_JSON).post(Entity.json(book));
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			JsonObject bookJson = response.readEntity(JsonObject.class);
			List<LinkResource> hypermedia = extractLinks(bookJson.getJsonArray("links"));
			Book savedBook = new Book.BookBuilder()
					.setId(String.valueOf(bookJson.getInt("id")))
					.setIsbn(bookJson.getString("isbn"))
					.setTitle(bookJson.getString("title"))
					.setHyperlinks(hypermedia)
					.createBook();
			bookEvent.fire(savedBook);
			return savedBook;
		} else {
			throw new ResponseProcessingException(response, response.readEntity(String.class));
		}
	}
	
    /**
     * Extracts the links from the json object
     *
     * @param linkArray the JSON array that contains the link list
     * @return list of links
     */
    private List<LinkResource> extractLinks(JsonArray linkArray) {

        List<LinkResource> links = new ArrayList<>();

        for (int j = 0; j < linkArray.size(); j++) {
            JsonObject jObject = linkArray.getJsonObject(j);
            String rel = jObject.getString("rel", "");
            String type = jObject.getString("type", "");
            String uri = jObject.getString("uri", "");
            links.add(new LinkResource(rel, type, URI.create(uri)));
        }

        return Collections.unmodifiableList(links);
    }

}
