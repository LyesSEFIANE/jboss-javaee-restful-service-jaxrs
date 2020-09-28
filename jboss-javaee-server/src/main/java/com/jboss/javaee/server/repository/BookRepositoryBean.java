package com.jboss.javaee.server.repository;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.jboss.javaee.server.model.Book;

/**
 * 
 * BookRepositoryBean.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-06
 *
 */
@Stateless
public class BookRepositoryBean implements BookRepository {

	@Inject
	private EntityManager em;

	@Override
	public List<Book> findAll() {
		TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);
		return query.getResultList();
	}

	@Override
	public Optional<Book> findByIsbn(String isbn) {
		try {
			TypedQuery<Book> query = em.createNamedQuery("Book.findByIsbn", Book.class);
			query.setParameter(1, isbn);
			return query.getResultList().stream().findFirst();
		} catch (RuntimeException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Book> save(Book book) {
		em.persist(book);
		return Optional.of(book);
	}
}
