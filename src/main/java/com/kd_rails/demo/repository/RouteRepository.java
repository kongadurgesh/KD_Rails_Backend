package com.kd_rails.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kd_rails.demo.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    boolean existsBySourceAndDestination(String source, String destination);
}
