package com.hacker.shift_service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse<Void> handleIllegalArgumentException(IllegalArgumentException ex) {
        return BaseResponse.error(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public BaseResponse<Void> handleNoSuchElementException(NoSuchElementException ex) {
        return BaseResponse.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<Void> handleGeneralException(Exception ex) {
        return BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }
}