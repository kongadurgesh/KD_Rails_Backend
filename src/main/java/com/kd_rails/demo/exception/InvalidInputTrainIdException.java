package com.kd_rails.demo.exception;

public class InvalidInputTrainIdException extends RuntimeException {
    private String trainId;

    public InvalidInputTrainIdException(String trainId) {
        this.trainId = trainId;
    }

    public String getTrainId() {
        return trainId;
    }

}
