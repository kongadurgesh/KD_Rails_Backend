package com.kd_rails.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kd_rails.demo.dto.RouteDTO;
import com.kd_rails.demo.dto.RouteDetailsDTO;
import com.kd_rails.demo.dto.TrainDTO;
import com.kd_rails.demo.entity.Route;
import com.kd_rails.demo.exception.InvalidInputRouteException;
import com.kd_rails.demo.exception.InvalidRouteException;
import com.kd_rails.demo.exception.RouteAlreadyExistsException;
import com.kd_rails.demo.exception.RouteDoesNotExistException;
import com.kd_rails.demo.repository.RouteRepository;
import com.kd_rails.demo.utility.RouteMapper;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TrainService trainService;

    @Override
    @Transactional
    public RouteDTO createRoute(RouteDTO routeDTO) {
        log.info("Creating Route with source: {}, destination: {}", routeDTO.getSource(), routeDTO.getDestination());

        if (routeRepository.existsBySourceAndDestination(routeDTO.getSource(), routeDTO.getDestination())) {
            throw new RouteAlreadyExistsException(routeDTO.getSource(), routeDTO.getDestination());
        }
        if (routeDTO.getSource().equalsIgnoreCase(routeDTO.getDestination())) {
            throw new InvalidRouteException(routeDTO.getSource());
        }
        Route route = RouteMapper.toEntity(routeDTO);
        Route savedRoute = routeRepository.save(route);
        return RouteMapper.toDTO(savedRoute);
    }

    @Override
    public RouteDetailsDTO getRouteDetails(String routeId) {
        log.info("Started Fetching Route Details with route ID: {}", routeId);

        Integer routeIdInteger;

        try {
            routeIdInteger = Integer.parseInt(routeId);
        } catch (Exception e) {
            throw new InvalidInputRouteException(routeId);
        }

        Route route = routeRepository.findById(routeIdInteger)
                .orElseThrow(() -> new RouteDoesNotExistException(routeIdInteger));

        List<TrainDTO> trains = trainService.getTrainsFromRoute(routeIdInteger);

        RouteDetailsDTO routeDetailsDTO = RouteDetailsDTO.builder()
                .source(route.getSource())
                .destination(route.getDestination())
                .trains(trains)
                .build();

        return routeDetailsDTO;
    }

}
