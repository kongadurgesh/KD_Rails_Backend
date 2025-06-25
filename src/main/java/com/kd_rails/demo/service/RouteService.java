package com.kd_rails.demo.service;

import org.springframework.validation.annotation.Validated;

import com.kd_rails.demo.dto.RouteDTO;

@Validated
public interface RouteService {
    RouteDTO createRoute(RouteDTO routeDTO);
}
