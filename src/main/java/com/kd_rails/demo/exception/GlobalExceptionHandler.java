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

        @ExceptionHandler(InvalidInputRouteException.class)
        public ResponseEntity<ErrorResponse> handleInvalidInputRouteException(
                        InvalidInputRouteException invalidInputRouteException, HttpServletRequest httpServletRequest,
                        Locale locale) {
                String errorMessage = messageSource.getMessage("error.route.invalid_route_input",
                                new Object[] { invalidInputRouteException.getRouteId() }, locale);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .message(errorMessage)
                                .error("Invalid Route ID")
                                .path(httpServletRequest.getRequestURI())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timeStamp(LocalDateTime.now())
                                .build();

                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(InvalidInputSourceAndDestinationException.class)
        public ResponseEntity<ErrorResponse> handleInvalidInputSourceAndDestinationException(
                        InvalidInputSourceAndDestinationException invalidInputSourceAndDestinationException,
                        HttpServletRequest httpServletRequest, Locale locale) {
                String errorMessage = messageSource.getMessage("error.route.invalid_source_and_destination",
                                new Object[] { invalidInputSourceAndDestinationException.getSource(),
                                                invalidInputSourceAndDestinationException.getDestination() },
                                locale);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("Invalid Source and Destination")
                                .message(errorMessage)
                                .path(httpServletRequest.getRequestURI())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timeStamp(LocalDateTime.now())
                                .build();
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(EmptySourceAndDestinationException.class)
        public ResponseEntity<ErrorResponse> handleEmptySourceAndDestinationException(
                        EmptySourceAndDestinationException emptySourceAndDestinationException,
                        HttpServletRequest httpServletRequest, Locale locale) {
                String errorMessage = messageSource.getMessage("error.route.empty_source_or_destination",
                                new Object[] { emptySourceAndDestinationException.getMessage() },
                                locale);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("Empty Source or Destination")
                                .message(errorMessage)
                                .path(httpServletRequest.getRequestURI())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timeStamp(LocalDateTime.now())
                                .build();
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(NoTrainsRunningFromSourceAndDestination.class)
        public ResponseEntity<ErrorResponse> handleNoTrainsRunningFromSourceAndDestination(
                        NoTrainsRunningFromSourceAndDestination noTrainsRunningFromSourceAndDestination,
                        HttpServletRequest httpServletRequest, Locale locale) {
                String errorMessage = messageSource.getMessage(
                                "error.route.no_trains_running_from_source_to_destination",
                                new Object[] { noTrainsRunningFromSourceAndDestination.getSource(),
                                                noTrainsRunningFromSourceAndDestination.getDestination() },
                                locale);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("No Trains found")
                                .message(errorMessage)
                                .path(httpServletRequest.getRequestURI())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timeStamp(LocalDateTime.now())
                                .build();
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(NoTrainsRunningInRouteException.class)
        public ResponseEntity<ErrorResponse> handleNoTrainsRunningInRoute(
                        NoTrainsRunningInRouteException noTrainsRunningInRouteException,
                        HttpServletRequest httpServletRequest, Locale locale) {
                String errorMessage = messageSource.getMessage(
                                "error.route.no_trains_running_in_route",
                                new Object[] { noTrainsRunningInRouteException.getRouteId() },
                                locale);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("No Trains running in the Route")
                                .message(errorMessage)
                                .path(httpServletRequest.getRequestURI())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timeStamp(LocalDateTime.now())
                                .build();
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(TrainNotRunningInRouteException.class)
        public ResponseEntity<ErrorResponse> handleTrainNotRunningInRoute(
                        TrainNotRunningInRouteException trainNotRunningInRouteException,
                        HttpServletRequest httpServletRequest, Locale locale) {
                String errorMessage = messageSource.getMessage(
                                "error.route.train_id_not_exist_in_route",
                                new Object[] { trainNotRunningInRouteException.getTrainId(),
                                                trainNotRunningInRouteException.getRouteId() },
                                locale);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("Train does not exist in the Route")
                                .message(errorMessage)
                                .path(httpServletRequest.getRequestURI())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timeStamp(LocalDateTime.now())
                                .build();
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(InvalidInputTrainIdException.class)
        public ResponseEntity<ErrorResponse> handleInvalidInputTrainIdException(
                        InvalidInputTrainIdException invalidInputTrainIdException,
                        HttpServletRequest httpServletRequest, Locale locale) {
                String errorMessage = messageSource.getMessage(
                                "error.train.train_id_does_not_exist",
                                new Object[] { invalidInputTrainIdException.getTrainId() },
                                locale);

                ErrorResponse errorResponse = ErrorResponse.builder()
                                .error("Train does not exist")
                                .message(errorMessage)
                                .path(httpServletRequest.getRequestURI())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .timeStamp(LocalDateTime.now())
                                .build();
                return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
        }
}
