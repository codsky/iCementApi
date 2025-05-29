package com.icement.api.iCement.Exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Item not found");
    }
    
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
