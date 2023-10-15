package com.blog.site.core.api.exception;

public class ValidationException extends Exception{
    private String message;
    public ValidationException(String message){
        super(message);
    }
}
