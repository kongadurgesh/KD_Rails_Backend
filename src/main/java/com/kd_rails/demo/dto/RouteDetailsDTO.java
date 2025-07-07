package com.kd_rails.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RouteDetailsDTO {
    private String source;
    private String destination;
    private List<TrainDTO> trains;
}
