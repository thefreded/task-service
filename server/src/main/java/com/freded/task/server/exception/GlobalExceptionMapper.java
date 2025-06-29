package com.freded.task.server.exception;

/*
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {

        LOG.error("Unhandled exception occurred: {}", exception.getMessage(), exception);


        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setStatusCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());



        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).dto(errorResponse).type(MediaType
        .APPLICATION_JSON).build();
    }
}

 */