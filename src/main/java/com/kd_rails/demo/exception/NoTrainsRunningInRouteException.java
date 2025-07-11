package com.kd_rails.demo.exception;

public class NoTrainsRunningInRouteException extends RuntimeException {
    private Integer routeId;

    public NoTrainsRunningInRouteException(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getRouteId() {
        return routeId;
    }

}
