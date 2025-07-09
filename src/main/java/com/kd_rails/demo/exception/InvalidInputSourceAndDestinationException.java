package com.kd_rails.demo.exception;

public class InvalidInputSourceAndDestinationException extends RuntimeException {
    private String source;
    private String destination;

    public InvalidInputSourceAndDestinationException(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

}
