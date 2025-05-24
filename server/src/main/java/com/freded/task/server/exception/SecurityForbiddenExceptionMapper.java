package com.freded.task.server.exception;

import io.quarkus.security.ForbiddenException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Handles Keycloak authorization/forbidden access
 */
@Provider
public class SecurityForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(io.quarkus.security.ForbiddenException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Insufficient permissions to access this resource");
        errorResponse.setStatusCode(Response.Status.FORBIDDEN.getStatusCode());

        return Response.status(Response.Status.FORBIDDEN).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
