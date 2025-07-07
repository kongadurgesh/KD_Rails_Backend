package com.kd_rails.demo.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.kd_rails.demo.dto.TrainDTO;

@Validated
public interface TrainService {
    TrainDTO createTrain(TrainDTO trainDTO);

    List<TrainDTO> getTrainsFromRoute(Integer routeId);
}
