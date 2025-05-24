package com.freded.task.server.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        ErrorResponse errorResponse = new ErrorResponse();

        StringBuilder errorMessage = new StringBuilder();
        exception.getConstraintViolations().forEach(v -> errorMessage.append(v.getPropertyPath()).append(" - ").append(v.getMessage()).append("; "));

        errorResponse.setMessage(errorMessage.toString());
        errorResponse.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }

}