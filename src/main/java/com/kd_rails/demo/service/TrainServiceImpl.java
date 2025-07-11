package com.kd_rails.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kd_rails.demo.dto.TrainDTO;
import com.kd_rails.demo.entity.Route;
import com.kd_rails.demo.entity.Train;
import com.kd_rails.demo.exception.NoTrainsRunningFromSourceAndDestination;
import com.kd_rails.demo.exception.RouteDoesNotExistException;
import com.kd_rails.demo.exception.TrainAlreadyExistsException;
import com.kd_rails.demo.exception.TrainNotRunningInRouteException;
import com.kd_rails.demo.repository.RouteRepository;
import com.kd_rails.demo.repository.TrainRepository;
import com.kd_rails.demo.utility.RouteUtils;
import com.kd_rails.demo.utility.TrainMapper;
import com.kd_rails.demo.utility.TrainUtils;

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
    public List<TrainDTO> getTrainsFromRoute(String routeId) {
        log.info("Fetching Trains with Route ID as :{}", routeId);

        Integer routeIdInteger = RouteUtils.validateRouteId(routeId);

        return trainRepository.getAllTrainsForRouteId(routeIdInteger).stream().map(train -> TrainMapper.toDTO(train))
                .toList();
    }

    @Override
    public List<TrainDTO> getTrainsFromSourceToDestination(String source, String destination) {
        log.info("Fetching Trains from source: {} and destination: {}", source, destination);

        RouteUtils.validate(source, destination);

        Route route = routeRepository.findFirstBySourceAndDestination(source, destination)
                .orElseThrow(() -> new NoTrainsRunningFromSourceAndDestination(source, destination));

        List<TrainDTO> trainDTOs = getTrainsFromRoute(route.getRouteId().toString());

        if (trainDTOs.isEmpty()) {
            throw new NoTrainsRunningFromSourceAndDestination(source, destination);
        }

        return trainDTOs;
    }

    @Override
    public void deleteTrainFromRoute(String trainId, String routeId) {
        Integer routeIdInteger = RouteUtils.validateRouteId(routeId);
        Integer trainIdInteger = TrainUtils.validateTrainId(trainId);

        if (!routeRepository.existsById(routeIdInteger)) {
            throw new RouteDoesNotExistException(routeIdInteger);
        }

        if (!trainRepository.existsByTrainIdAndRouteID(trainIdInteger, routeIdInteger)) {
            throw new TrainNotRunningInRouteException(trainIdInteger, routeIdInteger);
        }

        trainRepository.deleteTrainByRouteId(trainIdInteger, routeIdInteger);
    }

    @Override
    public TrainDTO updateTrainInRoute(String routeId, String trainId, TrainDTO trainDTO) {
        Integer routeIdInteger = RouteUtils.validateRouteId(routeId);
        Integer trainIdInteger = TrainUtils.validateTrainId(trainId);

        if (!routeRepository.existsById(routeIdInteger)) {
            throw new RouteDoesNotExistException(routeIdInteger);
        }

        Train toUpdate = trainRepository.findByTrainIdAndRouteID(trainIdInteger, routeIdInteger)
                .orElseThrow(() -> new TrainNotRunningInRouteException(trainIdInteger, routeIdInteger));

        Train updateTrain = TrainMapper.toEntity(trainDTO);

        toUpdate.setTrainName(updateTrain.getTrainName());
        toUpdate.setArrivalTime(updateTrain.getArrivalTime());
        toUpdate.setDepartureTime(updateTrain.getDepartureTime());
        toUpdate.setFare(updateTrain.getFare());

        Train updatedTrain = trainRepository.save(toUpdate);

        return TrainMapper.toDTO(updatedTrain);
    }

}
