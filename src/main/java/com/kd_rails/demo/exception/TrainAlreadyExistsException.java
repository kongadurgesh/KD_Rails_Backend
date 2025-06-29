package com.kd_rails.demo.exception;

public class TrainAlreadyExistsException extends RuntimeException {
    private String trainName;
    private Integer routeId;

    public TrainAlreadyExistsException(String trainName, Integer routeId) {
        super();
        this.trainName = trainName;
        this.routeId = routeId;
    }

    public String getTrainName() {
        return trainName;
    }

    public Integer getRouteId() {
        return routeId;
    }

}
