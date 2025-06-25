package com.kd_rails.demo.exception;

public class RouteAlreadyExistsException extends RuntimeException {
    public RouteAlreadyExistsException(String message) {
        super(message);
    }
}
