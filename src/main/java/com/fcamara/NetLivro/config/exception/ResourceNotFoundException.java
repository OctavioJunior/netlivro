package com.fcamara.NetLivro.config.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("recurso não encontrado");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
