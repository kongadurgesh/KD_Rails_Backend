package com.kd_rails.demo.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.kd_rails.demo.dto.TrainDTO;

@Validated
public interface TrainService {
    TrainDTO createTrain(TrainDTO trainDTO);

    List<TrainDTO> getTrainsFromRoute(String routeId);

    List<TrainDTO> getTrainsFromSourceToDestination(String source, String destination);

    void deleteTrainFromRoute(String trainId, String routeId);

    TrainDTO updateTrainInRoute(String routeId, String trainId, TrainDTO trainDTO);
}
