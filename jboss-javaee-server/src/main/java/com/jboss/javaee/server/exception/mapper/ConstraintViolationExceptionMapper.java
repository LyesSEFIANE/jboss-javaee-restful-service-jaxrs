package com.jboss.javaee.server.exception.mapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jboss.javaee.server.exception.entity.ValidationError;

/**
 * 
 * ConstraintViolationExceptionMapper.java
 *
 * @author Lyes Sefiane
 * @email lyes.sefiane@gmail.com
 * @date 2020-09-09
 *
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		List<ValidationError> errors = exception.getConstraintViolations()//
				.stream()//
				.map(this::toValidationError)//
				.collect(Collectors.toList());

		return Response.status(Status.BAD_REQUEST).entity(errors).type(MediaType.APPLICATION_JSON).build();
	}

	private ValidationError toValidationError(ConstraintViolation<?> constraintViolation) {
		ValidationError error = new ValidationError();
		error.setProperty(extractProperty(constraintViolation.getPropertyPath().toString()));
		error.setMessage(constraintViolation.getMessage());
		return error;
	}

	private String extractProperty(String path) {
		return path.substring(path.lastIndexOf('.') + 1);
	}

}
