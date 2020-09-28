package com.jboss.javaee.server.service;

import java.util.List;
import java.util.Optional;

import com.jboss.javaee.server.exception.BookAlreadyExistsException;
import com.jboss.javaee.server.model.Book;

/**
 * 
 * BookService.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-06
 *
 */
public interface BookService {

	List<Book> getAllBooks();

	Optional<Book> findBookByIsbn(String isbn);

	Optional<Book> save(Book book) throws BookAlreadyExistsException;

}
