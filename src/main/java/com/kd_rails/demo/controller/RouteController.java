package com.kd_rails.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kd_rails.demo.dto.RouteDTO;
import com.kd_rails.demo.dto.RouteDetailsDTO;
import com.kd_rails.demo.service.RouteService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/routes")
@Slf4j
public class RouteController {
    @Autowired
    private RouteService routeService;

    @PostMapping
    public ResponseEntity<RouteDTO> createRoute(@Valid @RequestBody RouteDTO routeDTO) {
        log.info("Recieved Request by controller to create Route");
        RouteDTO createdRouteDTO = routeService.createRoute(routeDTO);
        return new ResponseEntity<>(createdRouteDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<RouteDetailsDTO> getRouteDetails(@RequestParam String routeId) {
        log.info("Recieved Request by controller to get Route Details");
        RouteDetailsDTO routeDetailsDTO = routeService.getRouteDetails(routeId);

        return new ResponseEntity<>(routeDetailsDTO, HttpStatus.OK);
    }

    @PutMapping("/{routeId}")
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable String routeId, @Valid @RequestBody RouteDTO routeDTO) {
        log.info("Recieved Request by controller to update Route Details");
        RouteDTO updatedDTO = routeService.updateRoute(routeId, routeDTO);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }
}
