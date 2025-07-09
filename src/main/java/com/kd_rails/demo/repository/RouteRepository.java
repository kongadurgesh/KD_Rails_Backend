package com.kd_rails.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kd_rails.demo.entity.Route;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    boolean existsBySourceAndDestination(String source, String destination);

    List<Route> findBySourceAndDestination(String source, String destination);

    boolean existsBySource(String source);

    boolean existsByDestination(String destination);

    Optional<Route> findFirstBySourceAndDestination(String source, String destination);
}
