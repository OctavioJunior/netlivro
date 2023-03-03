package com.fcamara.NetLivro.exception.handler;

import com.fcamara.NetLivro.exception.ConflictException;
import com.fcamara.NetLivro.exception.GenericException;
import com.fcamara.NetLivro.exception.InvalidRequestException;
import com.fcamara.NetLivro.exception.ResourceNotFoundException;
import com.fcamara.NetLivro.exception.dto.ApiErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(status.value(), ex.getMessage());
        return new ResponseEntity(errorResponse, status);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiErrorResponseDto> handleInvalidRequestException(InvalidRequestException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(status.value(), ex.getMessage());
        return new ResponseEntity(errorResponse, status);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ApiErrorResponseDto> handleGenericException(GenericException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(status.value(), ex.getMessage());
        return new ResponseEntity(errorResponse, status);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponseDto> handleConflictException(ConflictException ex) {
        HttpStatus status = HttpStatus.CONFLICT;

        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(status.value(), ex.getMessage());
        return new ResponseEntity(errorResponse, status);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponseDto> handleAuthenticationException(AuthenticationException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ApiErrorResponseDto errorResponse = new ApiErrorResponseDto(status.value(), "credenciais inválidas");
        return new ResponseEntity(errorResponse, status);
    }
}
