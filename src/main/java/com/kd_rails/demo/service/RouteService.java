package com.kd_rails.demo.service;

import org.springframework.validation.annotation.Validated;

import com.kd_rails.demo.dto.RouteDTO;
import com.kd_rails.demo.dto.RouteDetailsDTO;

@Validated
public interface RouteService {
    RouteDTO createRoute(RouteDTO routeDTO);

    RouteDetailsDTO getRouteDetails(String routeId);
}
