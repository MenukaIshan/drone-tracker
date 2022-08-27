package com.musala.dronetracker.controller;

import com.musala.dronetracker.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DroneController {
    private static final Logger logger = LoggerFactory.getLogger(DroneController.class);
    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping("/drones")
    public ResponseEntity getAllDrones() {
        logger.info("Controller: Get all drones request received");
        return droneService.getAllDrones();
    }

    @GetMapping("/drones/{droneId}")
    public ResponseEntity getADroneDetails(@PathVariable(name = "droneId", required = true) Integer droneId) {
        logger.info("Controller: Get drone for id [{}]", droneId);
        return droneService.getDroneDetails(droneId);
    }

}
