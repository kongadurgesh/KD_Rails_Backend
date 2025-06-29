package com.kd_rails.demo.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainDTO {

    private Integer trainId;

    @NotBlank(message = "Train name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z]+)*$", message = "Train name can have only alphabets")
    private String trainName;

    @NotNull(message = "Arrival time cannot be blank")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date arrivalTime;

    @NotNull(message = "Departure time cannot be blank")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date departureTime;

    @NotNull(message = "Fare cannot be blank")
    @DecimalMin(value = "0.0", message = "Fare cannot be negative")
    private Double fare;

    @NotNull(message = "Route ID cannot be blank")
    @Digits(integer = 3, fraction = 0, message = "Route ID should be a 3 digit number")
    private Integer routeId;
}
