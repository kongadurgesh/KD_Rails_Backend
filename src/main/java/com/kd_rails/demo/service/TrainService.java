package com.kd_rails.demo.service;

import org.springframework.validation.annotation.Validated;

import com.kd_rails.demo.dto.TrainDTO;

@Validated
public interface TrainService {
    public TrainDTO createTrain(TrainDTO trainDTO);
}
