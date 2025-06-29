package com.kd_rails.demo.exception;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

        @Autowired
        private MessageSource messageSource;

        @ExceptionHandler(RouteAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleRouteAlreadyExists(
                        RouteAlreadyExistsException routeAlreadyExistsException, HttpServletRequest httpServletRequest,
                        Locale locale) {
                String errorMessage = messageSource.getMessage("error.route.exists",
                                new Object[] { routeAlreadyExistsException.getSource(),
                                                routeAlreadyExistsException.getDestination() },
                                locale);
                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("Route Exists")
                                .message(errorMessage)
                                .timeStamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .path(httpServletRequest.getRequestURI())
                                .build();
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        }

        @ExceptionHandler(InvalidRouteException.class)
        public ResponseEntity<ErrorResponse> handleInvalidRoute(InvalidRouteException invalidRouteException,
                        HttpServletRequest httpServletRequest, Locale locale) {
                String errorMessage = messageSource.getMessage("error.route.same_source_and_destination",
                                new Object[] { invalidRouteException.getSource() }, locale);
                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("Source and Destination are same")
                                .message(errorMessage)
                                .timeStamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .path(httpServletRequest.getRequestURI())
                                .build();
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(TrainAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleTrainAlreadyExists(
                        TrainAlreadyExistsException trainAlreadyExistsException, HttpServletRequest httpServletRequest,
                        Locale locale) {
                String errorMessage = messageSource.getMessage("error.train.exists", new Object[] {
                                trainAlreadyExistsException.getTrainName(), trainAlreadyExistsException.getRouteId() },
                                locale);
                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("Train already Exists")
                                .message(errorMessage)
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timeStamp(LocalDateTime.now())
                                .path(httpServletRequest.getRequestURI())
                                .build();
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(RouteDoesNotExistException.class)
        public ResponseEntity<ErrorResponse> handleRouteDoesNotExist(
                        RouteDoesNotExistException routeDoesNotExistException, HttpServletRequest httpServletRequest,
                        Locale locale) {
                String errorMessage = messageSource.getMessage("error.route.does_not_exist",
                                new Object[] { routeDoesNotExistException.getRouteId() }, locale);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("Route does not exist")
                                .message(errorMessage)
                                .timeStamp(LocalDateTime.now())
                                .path(httpServletRequest.getRequestURI())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .build();
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }
}
