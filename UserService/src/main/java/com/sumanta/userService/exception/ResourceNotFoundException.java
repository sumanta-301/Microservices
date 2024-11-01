package com.sumanta.userService.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;



public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException(){
        super("Resource Not Found on Server");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
