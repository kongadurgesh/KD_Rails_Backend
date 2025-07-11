package com.kd_rails.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kd_rails.demo.dto.TrainDTO;
import com.kd_rails.demo.service.TrainService;
import com.kd_rails.demo.utility.TrainUtils;

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

    @GetMapping
    public ResponseEntity<List<TrainDTO>> getTrainsFromSourceToDestination(
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String destination) {
        log.info("Recieved request by Train Controller to fetch trains");

        List<TrainDTO> trainDTOs = trainService.getTrainsFromSourceToDestination(source, destination);
        return new ResponseEntity<>(trainDTOs, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTrainFromRoute(@RequestParam(required = false) String trainId,
            @RequestParam(required = false) String routeId) {
        log.info("Recieved Request by Train Controller to delete Train {} from Route {}", trainId, routeId);

        trainService.deleteTrainFromRoute(trainId, routeId);

        return new ResponseEntity<String>(TrainUtils.getDeletedTrainMessage(trainId, routeId), HttpStatus.OK);
    }

    @PutMapping("/{routeId}/{trainId}")
    public ResponseEntity<TrainDTO> updateTrainInRoute(@PathVariable String routeId,
            @PathVariable String trainId, @RequestBody TrainDTO trainDTO) {
        log.info("Recieved Request Train Controller to Update Train {} on Route {}", trainId, routeId);

        return new ResponseEntity<TrainDTO>(trainService.updateTrainInRoute(routeId, trainId, trainDTO), HttpStatus.OK);
    }
}
