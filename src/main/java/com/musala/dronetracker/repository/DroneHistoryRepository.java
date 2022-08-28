package com.musala.dronetracker.repository;

import com.musala.dronetracker.model.DroneBatteryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneHistoryRepository extends JpaRepository<DroneBatteryStatus, Integer> {
}
