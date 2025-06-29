package com.kd_rails.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {
    private Integer routeId;

    @NotBlank(message = "Source cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Source can contain only alphabets")
    private String source;

    @NotBlank(message = "Destination cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Destination can contain only alphabets")
    private String destination;
}
