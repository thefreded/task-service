package com.freded.task.server.exception;

import io.quarkus.security.AuthenticationFailedException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Handles Keycloak authentication failures
 */
@Provider
public class SecurityAuthenticationExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {


    @Override
    public Response toResponse(io.quarkus.security.AuthenticationFailedException exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid credentials provided");
        errorResponse.setStatusCode(Response.Status.UNAUTHORIZED.getStatusCode());


        return Response.status(Response.Status.UNAUTHORIZED).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
