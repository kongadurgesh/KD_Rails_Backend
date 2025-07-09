package com.kd_rails.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kd_rails.demo.dto.TrainDTO;
import com.kd_rails.demo.entity.Train;
import com.kd_rails.demo.exception.NoTrainsRunningFromSourceAndDestination;
import com.kd_rails.demo.exception.RouteDoesNotExistException;
import com.kd_rails.demo.exception.TrainAlreadyExistsException;
import com.kd_rails.demo.repository.RouteRepository;
import com.kd_rails.demo.repository.TrainRepository;
import com.kd_rails.demo.utility.RouteUtils;
import com.kd_rails.demo.utility.TrainMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public TrainDTO createTrain(TrainDTO trainDTO) {
        log.info("Creating Train with Train Name: {}, Route ID: {}", trainDTO.getTrainName(), trainDTO.getRouteId());

        if (trainRepository.existsByTrainNameAndRouteID(trainDTO.getTrainName(), trainDTO.getRouteId())) {
            throw new TrainAlreadyExistsException(trainDTO.getTrainName(), trainDTO.getRouteId());
        } else if (!routeRepository.existsById(trainDTO.getRouteId())) {
            throw new RouteDoesNotExistException(trainDTO.getRouteId());
        }

        Train train = TrainMapper.toEntity(trainDTO);
        Train savedTrain = trainRepository.save(train);
        return TrainMapper.toDTO(savedTrain);
    }

    @Override
    public List<TrainDTO> getTrainsFromRoute(Integer routeId) {
        log.info("Fetching Trains with Route ID as :{}", routeId);

        return trainRepository.getAllTrainsForRouteId(routeId).stream().map(train -> TrainMapper.toDTO(train)).toList();
    }

    @Override
    public List<TrainDTO> getTrainsFromSourceToDestination(String source, String destination) {
        log.info("Fetching Trains from source: {} and destination: {}", source, destination);

        RouteUtils.validate(source, destination);

        if (!routeRepository.existsBySourceAndDestination(source, destination)) {
            throw new NoTrainsRunningFromSourceAndDestination(source, destination);
        }

        Integer routeId = routeRepository.findBySourceAndDestination(source, destination).get(0).getRouteId();

        List<TrainDTO> trainDTOs = getTrainsFromRoute(routeId);

        if (trainDTOs.isEmpty()) {
            throw new NoTrainsRunningFromSourceAndDestination(source, destination);
        }
        return trainDTOs;
    }
}
