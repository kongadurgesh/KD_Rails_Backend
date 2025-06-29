package com.kd_rails.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kd_rails.demo.dto.TrainDTO;
import com.kd_rails.demo.service.TrainService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/trains")
@Slf4j
public class TrainController {
    @Autowired
    private TrainService trainService;

    @PostMapping
    public ResponseEntity<TrainDTO> createTrain(@Valid @RequestBody TrainDTO trainDTO) {
        log.info("Received Request by Train Controller to create Train");
        TrainDTO createdTrainDTO = trainService.createTrain(trainDTO);
        return new ResponseEntity<>(createdTrainDTO, HttpStatus.CREATED);
    }
}
