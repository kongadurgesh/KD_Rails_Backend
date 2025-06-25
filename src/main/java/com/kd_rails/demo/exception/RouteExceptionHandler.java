package com.kd_rails.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RouteExceptionHandler {
    @ExceptionHandler(RouteAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleRouteAlreadyExists(
            RouteAlreadyExistsException routeAlreadyExistsException, HttpServletRequest httpServletRequest) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("{route.exists}")
                .message(routeAlreadyExistsException.getMessage())
                .timeStamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
