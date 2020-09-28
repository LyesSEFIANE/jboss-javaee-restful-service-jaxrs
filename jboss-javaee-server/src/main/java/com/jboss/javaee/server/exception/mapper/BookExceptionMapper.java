package com.jboss.javaee.server.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jboss.javaee.server.exception.BookException;
import com.jboss.javaee.server.exception.entity.ErrorMessage;

/**
 * 
 * BookExceptionMapper.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-07
 *
 */
@Provider
public class BookExceptionMapper implements ExceptionMapper<BookException> {

	@Override
	public Response toResponse(BookException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}

}
