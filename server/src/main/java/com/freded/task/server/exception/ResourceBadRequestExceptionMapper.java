package com.freded.task.server.exception;


import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceBadRequestExceptionMapper implements ExceptionMapper<ResourceBadRequestException> {
    @Override
    public Response toResponse(ResourceBadRequestException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Hello");
        errorResponse.setStatusCode(Response.Status.BAD_REQUEST.getStatusCode());

        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
