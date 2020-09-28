package com.jboss.javaee.server.repository;

import java.util.List;
import java.util.Optional;

import com.jboss.javaee.server.model.Book;

/**
 * 
 * BookRepository.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-06
 *
 */
public interface BookRepository {

	List<Book> findAll();

	Optional<Book> findByIsbn(String isbn);

	Optional<Book> save(Book book);

}
