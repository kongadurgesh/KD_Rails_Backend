package com.kd_rails.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kd_rails.demo.entity.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, String> {
    boolean existsByTrainNameAndRouteID(String trainName, Integer routeId);
}
