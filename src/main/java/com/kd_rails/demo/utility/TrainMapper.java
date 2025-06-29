package com.kd_rails.demo.utility;

import com.kd_rails.demo.dto.TrainDTO;
import com.kd_rails.demo.entity.Train;

public class TrainMapper {
    public static TrainDTO toDTO(Train train) {
        return TrainDTO.builder()
                .trainName(train.getTrainName())
                .trainId(train.getTrainId())
                .arrivalTime(train.getArrivalTime())
                .departureTime(train.getDepartureTime())
                .fare(train.getFare())
                .routeId(train.getRouteID())
                .build();
    }

    public static Train toEntity(TrainDTO trainDTO) {
        return Train.builder()
                .trainId(trainDTO.getTrainId())
                .trainName(trainDTO.getTrainName())
                .arrivalTime(trainDTO.getArrivalTime())
                .departureTime(trainDTO.getDepartureTime())
                .fare(trainDTO.getFare())
                .routeID(trainDTO.getRouteId())
                .build();
    }
}
