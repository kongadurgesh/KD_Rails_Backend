package com.kd_rails.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_sequence_gen")
    @SequenceGenerator(name = "route_sequence_gen", sequenceName = "route_sequence", allocationSize = 1)
    @Column(name = "route_id")
    private Integer routeId;

    @NotBlank(message = "Source cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Source can contain only alphabets")
    private String source;

    @NotBlank(message = "Destination cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Destination can contain only alphabets")
    private String destination;

}
