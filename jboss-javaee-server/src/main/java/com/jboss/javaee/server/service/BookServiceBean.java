package com.jboss.javaee.server.service;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.jboss.javaee.server.exception.BookAlreadyExistsException;
import com.jboss.javaee.server.exception.BookException;
import com.jboss.javaee.server.exception.entity.ErrorMessages;
import com.jboss.javaee.server.model.Book;
import com.jboss.javaee.server.repository.BookRepository;

/**
 * 
 * BookServiceBean.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-06
 *
 */
@Stateless
public class BookServiceBean implements BookService {

	@Inject
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> findBookByIsbn(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}

	@Override
	public Optional<Book> save(Book book) throws BookAlreadyExistsException {
		Optional<Book> existingBook = findBookByIsbn(book.getIsbn());
		if(existingBook.isPresent()) {
			throw new BookAlreadyExistsException(ErrorMessages.BOOK_ALREADY_EXISTS.getErrorMessage());
		}
		return bookRepository.save(book);
	}

}
