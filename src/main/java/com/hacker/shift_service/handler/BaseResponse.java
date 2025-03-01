package com.hacker.shift_service.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;

    private BaseResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), "Success", data);
    }

    public static <T> BaseResponse<T> error(HttpStatus status, String message) {
        return new BaseResponse<>(status.value(), message, null);
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}