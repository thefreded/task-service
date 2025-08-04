package com.freded.task.server.exception;

public class ResourceBadRequestException extends  RuntimeException{
    public ResourceBadRequestException(String message){
        super(message);
    }
}
