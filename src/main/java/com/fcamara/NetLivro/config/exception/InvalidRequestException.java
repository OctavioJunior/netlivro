package com.fcamara.NetLivro.config.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("requisição inválida");
    }

    public InvalidRequestException(String message) {
        super(message);
    }
}
