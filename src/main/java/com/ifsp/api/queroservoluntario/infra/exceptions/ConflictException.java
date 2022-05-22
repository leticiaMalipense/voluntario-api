package com.ifsp.api.queroservoluntario.infra.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException() {
        super();
    }

    public ConflictException(String message) {
        super(message);
    }
}
