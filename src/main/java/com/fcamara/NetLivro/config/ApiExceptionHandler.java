package com.fcamara.NetLivro.config;

import com.fcamara.NetLivro.config.exception.ConflictException;
import com.fcamara.NetLivro.config.exception.GenericException;
import com.fcamara.NetLivro.config.exception.InvalidRequestException;
import com.fcamara.NetLivro.config.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiErrorResponse errorResponse = new ApiErrorResponse(status.value(), ex.getMessage());
        return new ResponseEntity(errorResponse, status);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiErrorResponse errorResponse = new ApiErrorResponse(status.value(), ex.getMessage());
        return new ResponseEntity(errorResponse, status);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(GenericException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiErrorResponse errorResponse = new ApiErrorResponse(status.value(), ex.getMessage());
        return new ResponseEntity(errorResponse, status);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleConflictException(ConflictException ex) {
        HttpStatus status = HttpStatus.CONFLICT;

        ApiErrorResponse errorResponse = new ApiErrorResponse(status.value(), ex.getMessage());
        return new ResponseEntity(errorResponse, status);
    }
}
