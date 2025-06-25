package com.kd_rails.demo.exception;

public class InvalidRouteException extends RuntimeException {
    private String source;

    public InvalidRouteException(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

}
