package com.musala.dronetracker.service;

import com.musala.dronetracker.model.Drone;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DroneService {
    ResponseEntity getAllDrones();

    ResponseEntity getDroneDetails(Integer droneId);
}
