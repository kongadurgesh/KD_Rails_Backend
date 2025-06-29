package com.kd_rails.demo.exception;

public class RouteDoesNotExistException extends RuntimeException {
    private Integer routeId;

    public RouteDoesNotExistException(Integer routeId) {
        super();
        this.routeId = routeId;
    }

    public Integer getRouteId() {
        return routeId;
    }

}
