package com.musala.dronetracker.repository;

import com.musala.dronetracker.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Integer> {
}
