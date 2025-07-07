package com.kd_rails.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kd_rails.demo.entity.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
    boolean existsByTrainNameAndRouteID(String trainName, Integer routeId);

    @Query(value = "select * from trains t where t.route_id=:route_id", nativeQuery = true)
    List<Train> getAllTrainsForRouteId(@Param("route_id") Integer routeId);
}
