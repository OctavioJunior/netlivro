package com.fcamara.NetLivro.config.exception;

public class GenericException extends RuntimeException {
    public GenericException() {
        super("erro interno do servidor");
    }

    public GenericException(String message) {
        super(message);
    }
}
