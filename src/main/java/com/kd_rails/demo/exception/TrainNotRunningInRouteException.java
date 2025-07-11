package com.kd_rails.demo.exception;

public class TrainNotRunningInRouteException extends RuntimeException {
    private Integer trainId;
    private Integer routeId;

    public TrainNotRunningInRouteException(Integer trainId, Integer routeId) {
        this.trainId = trainId;
        this.routeId = routeId;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public Integer getRouteId() {
        return routeId;
    }

}
