package com.fcamara.NetLivro.exception;

public class ConflictException extends RuntimeException {
    public ConflictException() {
        super("ocorreu um conflito no servidor");
    }

    public ConflictException(String message) {
        super(message);
    }
}
