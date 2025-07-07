package com.kd_rails.demo.exception;

public class InvalidInputRouteException extends RuntimeException {
    private String routeId;

    public InvalidInputRouteException(String routeId) {
        super();
        this.routeId = routeId;
    }

    public String getRouteId() {
        return routeId;
    }
}
