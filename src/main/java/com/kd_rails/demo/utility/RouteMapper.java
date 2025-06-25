package com.kd_rails.demo.utility;

import com.kd_rails.demo.dto.RouteDTO;
import com.kd_rails.demo.entity.Route;

public class RouteMapper {
    public static Route toEntity(RouteDTO routeDTO) {
        return Route.builder()
                .routeId(routeDTO.getRouteId())
                .source(routeDTO.getSource())
                .destination(routeDTO.getDestination())
                .build();

    }

    public static RouteDTO toDTO(Route route) {
        return RouteDTO.builder()
                .routeId(route.getRouteId())
                .source(route.getSource())
                .destination(route.getDestination())
                .build();
    }
}
